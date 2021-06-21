//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.04.19 a las 04:43:15 PM CEST 
//


package com.inetum.livetooling.apps.tagReader.data.model.lista;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Numero_consecutivo_envio"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Numero_registros"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Registro" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Numero_TAG"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="21"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Operador_TAG"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *                         &lt;maxInclusive value="999"/&gt;
 *                         &lt;minInclusive value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Tipo_TAG"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *                         &lt;totalDigits value="3"/&gt;
 *                         &lt;enumeration value="1"/&gt;
 *                         &lt;enumeration value="2"/&gt;
 *                         &lt;enumeration value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Categoria_TAG"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;union&gt;
 *                         &lt;simpleType&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                             &lt;pattern value="[ \s]*"/&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/simpleType&gt;
 *                         &lt;simpleType&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *                             &lt;maxInclusive value="999"/&gt;
 *                             &lt;minInclusive value="1"/&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/simpleType&gt;
 *                       &lt;/union&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Status"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *                         &lt;totalDigits value="3"/&gt;
 *                         &lt;enumeration value="1"/&gt;
 *                         &lt;enumeration value="2"/&gt;
 *                         &lt;enumeration value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Hora_actualizacion"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;union&gt;
 *                         &lt;simpleType&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                             &lt;length value="0"/&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/simpleType&gt;
 *                         &lt;simpleType&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}time"&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/simpleType&gt;
 *                       &lt;/union&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Fecha_actualizacion"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;union&gt;
 *                         &lt;simpleType&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                             &lt;length value="0"/&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/simpleType&gt;
 *                         &lt;simpleType&gt;
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                             &lt;length value="10"/&gt;
 *                             &lt;pattern value="^([1-9]|([012][0-9])|(3[01]))-([0]{0,1}[1-9]|1[012])-\d\d\d\d$"/&gt;
 *                           &lt;/restriction&gt;
 *                         &lt;/simpleType&gt;
 *                       &lt;/union&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Importe_saldo" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
 *                         &lt;totalDigits value="15"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numeroConsecutivoEnvio",
    "numeroRegistros",
    "registro"
})
@XmlRootElement(name = "Lista")
public class Lista {

    @XmlElement(name = "Numero_consecutivo_envio")
    protected long numeroConsecutivoEnvio;
    @XmlElement(name = "Numero_registros")
    protected long numeroRegistros;
    @XmlElement(name = "Registro", required = true)
    protected List<Registro> registro;

    /**
     * Obtiene el valor de la propiedad numeroConsecutivoEnvio.
     * 
     */
    public long getNumeroConsecutivoEnvio() {
        return numeroConsecutivoEnvio;
    }

    /**
     * Define el valor de la propiedad numeroConsecutivoEnvio.
     * 
     */
    public void setNumeroConsecutivoEnvio(long value) {
        this.numeroConsecutivoEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroRegistros.
     * 
     */
    public long getNumeroRegistros() {
        return numeroRegistros;
    }

    /**
     * Define el valor de la propiedad numeroRegistros.
     * 
     */
    public void setNumeroRegistros(long value) {
        this.numeroRegistros = value;
    }

    /**
     * Gets the value of the registro property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registro property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistro().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Registro }
     * 
     * 
     */
    public List<Registro> getRegistro() {
        if (registro == null) {
            registro = new ArrayList<Registro>();
        }
        return this.registro;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Numero_TAG"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="21"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Operador_TAG"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
     *               &lt;maxInclusive value="999"/&gt;
     *               &lt;minInclusive value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Tipo_TAG"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
     *               &lt;totalDigits value="3"/&gt;
     *               &lt;enumeration value="1"/&gt;
     *               &lt;enumeration value="2"/&gt;
     *               &lt;enumeration value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Categoria_TAG"&gt;
     *           &lt;simpleType&gt;
     *             &lt;union&gt;
     *               &lt;simpleType&gt;
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                   &lt;pattern value="[ \s]*"/&gt;
     *                 &lt;/restriction&gt;
     *               &lt;/simpleType&gt;
     *               &lt;simpleType&gt;
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
     *                   &lt;maxInclusive value="999"/&gt;
     *                   &lt;minInclusive value="1"/&gt;
     *                 &lt;/restriction&gt;
     *               &lt;/simpleType&gt;
     *             &lt;/union&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Status"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
     *               &lt;totalDigits value="3"/&gt;
     *               &lt;enumeration value="1"/&gt;
     *               &lt;enumeration value="2"/&gt;
     *               &lt;enumeration value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Hora_actualizacion"&gt;
     *           &lt;simpleType&gt;
     *             &lt;union&gt;
     *               &lt;simpleType&gt;
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                   &lt;length value="0"/&gt;
     *                 &lt;/restriction&gt;
     *               &lt;/simpleType&gt;
     *               &lt;simpleType&gt;
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}time"&gt;
     *                 &lt;/restriction&gt;
     *               &lt;/simpleType&gt;
     *             &lt;/union&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Fecha_actualizacion"&gt;
     *           &lt;simpleType&gt;
     *             &lt;union&gt;
     *               &lt;simpleType&gt;
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                   &lt;length value="0"/&gt;
     *                 &lt;/restriction&gt;
     *               &lt;/simpleType&gt;
     *               &lt;simpleType&gt;
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                   &lt;length value="10"/&gt;
     *                   &lt;pattern value="^([1-9]|([012][0-9])|(3[01]))-([0]{0,1}[1-9]|1[012])-\d\d\d\d$"/&gt;
     *                 &lt;/restriction&gt;
     *               &lt;/simpleType&gt;
     *             &lt;/union&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Importe_saldo" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long"&gt;
     *               &lt;totalDigits value="15"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "numeroTAG",
        "operadorTAG",
        "tipoTAG",
        "categoriaTAG",
        "status",
        "horaActualizacion",
        "fechaActualizacion",
        "importeSaldo"
    })
    public static class Registro {

        @XmlElement(name = "Numero_TAG", required = true)
        protected String numeroTAG;
        @XmlElement(name = "Operador_TAG")
        protected int operadorTAG;
        @XmlElement(name = "Tipo_TAG")
        protected int tipoTAG;
        @XmlElement(name = "Categoria_TAG", required = true)
        protected String categoriaTAG;
        @XmlElement(name = "Status")
        protected int status;
        @XmlElement(name = "Hora_actualizacion", required = true)
        protected String horaActualizacion;
        @XmlElement(name = "Fecha_actualizacion", required = true)
        protected String fechaActualizacion;
        @XmlElement(name = "Importe_saldo")
        protected Long importeSaldo;

        /**
         * Obtiene el valor de la propiedad numeroTAG.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroTAG() {
            return numeroTAG;
        }

        /**
         * Define el valor de la propiedad numeroTAG.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroTAG(String value) {
            this.numeroTAG = value;
        }

        /**
         * Obtiene el valor de la propiedad operadorTAG.
         * 
         */
        public int getOperadorTAG() {
            return operadorTAG;
        }

        /**
         * Define el valor de la propiedad operadorTAG.
         * 
         */
        public void setOperadorTAG(int value) {
            this.operadorTAG = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoTAG.
         * 
         */
        public int getTipoTAG() {
            return tipoTAG;
        }

        /**
         * Define el valor de la propiedad tipoTAG.
         * 
         */
        public void setTipoTAG(int value) {
            this.tipoTAG = value;
        }

        /**
         * Obtiene el valor de la propiedad categoriaTAG.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCategoriaTAG() {
            return categoriaTAG;
        }

        /**
         * Define el valor de la propiedad categoriaTAG.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCategoriaTAG(String value) {
            this.categoriaTAG = value;
        }

        /**
         * Obtiene el valor de la propiedad status.
         * 
         */
        public int getStatus() {
            return status;
        }

        /**
         * Define el valor de la propiedad status.
         * 
         */
        public void setStatus(int value) {
            this.status = value;
        }

        /**
         * Obtiene el valor de la propiedad horaActualizacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHoraActualizacion() {
            return horaActualizacion;
        }

        /**
         * Define el valor de la propiedad horaActualizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHoraActualizacion(String value) {
            this.horaActualizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaActualizacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaActualizacion() {
            return fechaActualizacion;
        }

        /**
         * Define el valor de la propiedad fechaActualizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaActualizacion(String value) {
            this.fechaActualizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad importeSaldo.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getImporteSaldo() {
            return importeSaldo;
        }

        /**
         * Define el valor de la propiedad importeSaldo.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setImporteSaldo(Long value) {
            this.importeSaldo = value;
        }

    }

}
