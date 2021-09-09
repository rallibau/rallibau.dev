package com.rallibau.bpm.processFile.infrastructure;

import com.rallibau.bpm.connection.domain.*;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeName;
import com.rallibau.bpm.node.domain.NodeType;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessName;
import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.bpm.processFile.domain.BpmModelExtractor;
import com.rallibau.bpm.processFile.domain.BpmModelExtractorException;
import com.rallibau.shared.domain.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class FileProcessorXmlImpl implements BpmModelExtractor {

    public static final String PROCESS = "semantic:process";
    public static final String PROCESS_NAME = "name";
    public static final String COMMON_EXCEPTION = "Error processing xml process";
    public static final String ID = "id";
    public static final String INCOMMING = "semantic:incoming";
    public static final String OUTGOING = "semantic:outgoing";

    @Override
    public List<BpmModel> extractProcess(String filePath) throws BpmModelExtractorException {
        try {
            List<BpmModel> bpmModels = new ArrayList<>();
            File file = new File(filePath);
            NodeList nodeList = openDocument(file);
            for (int nodesPointer = 0; nodesPointer < nodeList.getLength(); nodesPointer++) {
                extractProcess(bpmModels, nodeList, nodesPointer);
            }
            return bpmModels;
        } catch (Exception e) {
            throw new BpmModelExtractorException(COMMON_EXCEPTION, e);
        }
    }

    private void extractProcess(List<BpmModel> bpmModels, NodeList nList, int temp) {
        BpmModel bpmProcessModel = new BpmModel(new ArrayList<>(), new ArrayList<>());
        HashMap<String,String> incomingConection = new HashMap<>();

        Node node = nList.item(temp);
        Element element = (Element) node;
        String processUid = generateUuid(element.getAttribute(ID)).toString();
        bpmProcessModel.setProcess(new Process(
                new ProcessId(processUid),
                new ProcessName(node.getNodeName()),
                new ArrayList<>()));

        bpmModels.add(bpmProcessModel);


        extractNodes(bpmProcessModel, node,incomingConection);
        extractConnection(bpmProcessModel, node,incomingConection);
    }

    private void extractConnection(BpmModel bpmProcessModel, Node nNode,HashMap<String,String> incomingConection) {
        NodeList processTask = nNode.getChildNodes();


        for (int nodesPointer = 0; nodesPointer < processTask.getLength(); nodesPointer++) {
            Node node = processTask.item(nodesPointer);
            if (node instanceof Element == false) {
                continue;
            }
            Element element = (Element) node;
            if (!NodeType.NODE_TYPE.existNodeType(element.getTagName())) {
                continue;
            }
            String nodeUid = generateUuid(element.getAttribute(ID)).toString();
            processOutgoingConnection(nodeUid, node, bpmProcessModel,incomingConection);
        }

    }

    private void extractNodes(BpmModel bpmProcessModel, Node nNode,HashMap<String,String> incomingConection) {
        NodeList processTask = nNode.getChildNodes();


        for (int nodesPointer = 0; nodesPointer < processTask.getLength(); nodesPointer++) {
            extractNode(bpmProcessModel, processTask, nodesPointer,incomingConection);
        }

    }

    private void extractNode(BpmModel bpmProcessModel, NodeList processTask, int nodesPointer,HashMap<String,String> incomingConection) {
        Node node = processTask.item(nodesPointer);
        if (node instanceof Element == false) {
            return;
        }
        Element element = (Element) node;
        if (!NodeType.NODE_TYPE.existNodeType(element.getTagName())) {
            return;
        }
        String nodeUid = generateUuid(element.getAttribute(ID)).toString();
        com.rallibau.bpm.node.domain.Node nodeDomain = new com.rallibau.bpm.node.domain.Node(new NodeId(nodeUid),
                new NodeName(element.getAttribute(PROCESS_NAME)),
                new NodeType(NodeType.NODE_TYPE.valueByTagName(element.getTagName()).get()));
        bpmProcessModel.nodes().add(nodeDomain);

        bpmProcessModel.process().nodes().add(new NodeId(nodeUid));

        processIncommingConnection(nodeUid,node,incomingConection);

    }



    private void processIncommingConnection(String idNodeParent, Node nNode,  HashMap<String,String> incomingConection) {

        NodeList processTask = nNode.getChildNodes();
        for (int nodesPointer = 0; nodesPointer < processTask.getLength(); nodesPointer++) {
            Node node = processTask.item(nodesPointer);
            if (node instanceof Element == false) {
                continue;
            }
            Element element = (Element) node;
            if (element.getTextContent() == null || element.getTextContent().isEmpty()) {
                continue;
            }
            Connection connection = null;
            if (element.getTagName().equals(INCOMMING)) {
                incomingConection.put(element.getTextContent(),idNodeParent);
            }
        }
    }

    private void processOutgoingConnection(String idNodeParent, Node nNode, BpmModel bpmProcessModel, HashMap<String, String> incomingConection) {

        NodeList processTask = nNode.getChildNodes();
        for (int nodesPointer = 0; nodesPointer < processTask.getLength(); nodesPointer++) {
            Node node = processTask.item(nodesPointer);
            if (node instanceof Element == false) {
                continue;
            }
            Element element = (Element) node;
            if (element.getTextContent() == null || element.getTextContent().isEmpty()) {
                continue;
            }

            Connection connection = null;
            if (element.getTagName().equals(OUTGOING)) {
                connection = Connection.create(new ConnectionId(generateUuid(idNodeParent + element.getTextContent()).toString()),
                        new NodeIdOwner(idNodeParent),
                        new NodeIdTarget(incomingConection.get(element.getTextContent())));
            }
            if(connection !=null) {
                bpmProcessModel.connections().add(connection);
            }

        }
    }


    private NodeList openDocument(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(PROCESS);
        return nodeList;
    }

    private static UUID generateUuid(String seed) {
        try {
            return UUID.nameUUIDFromBytes(seed.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(String.format("UnsupportedEncodingException: %f", e.getMessage()));
        }
    }
}
