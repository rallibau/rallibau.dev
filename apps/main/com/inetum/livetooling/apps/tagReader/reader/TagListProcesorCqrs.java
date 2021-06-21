package com.inetum.livetooling.apps.tagReader.reader;

import com.inetum.livetooling.apps.tagReader.data.model.lista.Lista;
import com.inetum.livetooling.lista.tag.application.create.CreateTagCommand;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.UUID;


@Service
public class TagListProcesorCqrs implements TagListProcesor{

    private static final Logger logger = LogManager.getLogger(TagListProcesorCqrs.class);


    private final CommandBus commandBus;

    public TagListProcesorCqrs(CommandBus commandBus) {
        this.commandBus = commandBus;
    }


    public void processFile(File file) {

        try {
            Lista lista = readFullList(file);
            logger.info("numero de registros : {}",lista.getNumeroRegistros());
            List<Lista.Registro> registros = lista.getRegistro();
            for(Lista.Registro registro: registros) {
                CreateTagCommand command = new CreateTagCommand(UUID.randomUUID().toString(),
                       registro.getNumeroTAG(),
                        UUID.randomUUID().toString(),
                        registro.getOperadorTAG(),
                        registro.getTipoTAG(),
                        registro.getCategoriaTAG(),
                        registro.getStatus(),
                        registro.getHoraActualizacion(),
                        registro.getFechaActualizacion(),
                        registro.getImporteSaldo().toString());

                commandBus.dispatch(command);
            }
        } catch (JAXBException e) {
            logger.error("Error in the Lista unmarshal process: {}", e.getMessage());
        }


    }

    private Lista readFullList(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Lista.class);
        return (Lista)jaxbContext.createUnmarshaller().unmarshal(file);
     }


}
