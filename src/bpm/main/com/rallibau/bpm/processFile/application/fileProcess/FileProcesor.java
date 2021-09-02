package com.rallibau.bpm.processFile.application.fileProcess;

import com.rallibau.bpm.node.application.create.CreateNodeCommand;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeName;
import com.rallibau.bpm.node.domain.NodeType;
import com.rallibau.bpm.process.application.create.CreateProcessCommand;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessName;
import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandBus;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileProcesor {

    private final CommandBus commandBus;

    public FileProcesor(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    public List<BpmModel> process(File file) throws ParserConfigurationException, IOException, SAXException {
        List<BpmModel> bpmModels = new ArrayList<>();

        NodeList nList = openDocument(file);
        for (int temp = 0; temp < nList.getLength(); temp++) {
            extractProcess(bpmModels, nList, temp);


        }

        return bpmModels;
    }

    public void persist(List<BpmModel> bpmModels) {
        bpmModels.stream().forEach(bpmModel -> commandBus.dispatch(new CreateProcessCommand(bpmModel.process().id().value(),
                bpmModel.process().name().value(), bpmModel.process().nodes().stream().map(NodeId::value).collect(Collectors.toList()))));

        bpmModels.stream().forEach(bpmModel -> bpmModel.nodes().stream().forEach(node -> commandBus.dispatch(new CreateNodeCommand(node.id().value(),
                node.name().value(),
                node.nodeType().value()))));
    }

    private void extractProcess(List<BpmModel> bpmModels, NodeList nList, int temp) {
        BpmModel bpmProcessModel = new BpmModel(new ArrayList<>());
        String processUid = UUID.randomUUID().toString();

        Node nNode = nList.item(temp);
        Element eElement = (Element) nNode;
        bpmProcessModel.setProcess(new Process(
                new ProcessId(processUid),
                new ProcessName(nNode.getNodeName()),
                new ArrayList<NodeId>()));

        bpmModels.add(bpmProcessModel);


        extractNodes(bpmProcessModel, nNode);
    }

    private void extractNodes(BpmModel bpmProcessModel, Node nNode) {
        NodeList processTask = nNode.getChildNodes();
        for (int temp2 = 0; temp2 < processTask.getLength(); temp2++) {
            Node nNode2 = processTask.item(temp2);
            String nodeUid = UUID.randomUUID().toString();
            if (nNode2 instanceof Element) {
                Element eElement2 = (Element) nNode2;
                bpmProcessModel.nodes().add(new com.rallibau.bpm.node.domain.Node(new NodeId(nodeUid),
                        new NodeName(eElement2.getAttribute("name")),
                        new NodeType(NodeType.NODE_TYPE.TASK)));

                bpmProcessModel.process().nodes().add(new NodeId(nodeUid));
              /*  System.out.println("                 Current task :" + nNode2.getNodeName());
                System.out.println("                 task id : " + eElement2.getAttribute("id"));
                System.out.println("                 task name : " + eElement2.getAttribute("name"));
                System.out.println("                 ----------------------------");*/
            }

        }
    }

    private NodeList openDocument(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("semantic:process");
        return nList;
    }


}
