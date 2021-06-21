package com.inetum.livetooling.apps.tagReader.reader;

import com.inetum.livetooling.apps.tagReader.data.model.lista.Lista;
import com.inetum.livetooling.shared.domain.bus.event.EventBus;
import com.inetum.livetooling.shared.domain.events.lista.TagReceivedDomainEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.UUID;


@Service
@Primary
public class TagListProcesorDomainEvent implements TagListProcesor{

    private static final Logger logger = LogManager.getLogger(TagListProcesorDomainEvent.class);

    private  EventBus eventBus;

    public TagListProcesorDomainEvent(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    public void processFile(File file) {

        try {
            Lista lista = readFullList(file);
            logger.info("numero de registros : {}",lista.getNumeroRegistros());
            List<Lista.Registro> registros = lista.getRegistro();
            for(Lista.Registro registro: registros) {
                TagReceivedDomainEvent event = new TagReceivedDomainEvent(UUID.randomUUID().toString(),
                       registro.getNumeroTAG(),
                        UUID.randomUUID().toString(),
                        registro.getOperadorTAG(),
                        registro.getTipoTAG(),
                        registro.getCategoriaTAG(),
                        registro.getStatus(),
                        registro.getHoraActualizacion(),
                        registro.getFechaActualizacion(),
                        registro.getImporteSaldo().toString());

                eventBus.publish(event);
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
