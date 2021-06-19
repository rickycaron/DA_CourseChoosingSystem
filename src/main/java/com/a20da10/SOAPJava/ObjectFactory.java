
package com.a20da10.SOAPJava;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.a20da10.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetByName_QNAME = new QName("http://SOAP.a20da10.com/", "getByName");
    private final static QName _GetAllResponse_QNAME = new QName("http://SOAP.a20da10.com/", "getAllResponse");
    private final static QName _GetByEmail_QNAME = new QName("http://SOAP.a20da10.com/", "getByEmail");
    private final static QName _GetByEmailResponse_QNAME = new QName("http://SOAP.a20da10.com/", "getByEmailResponse");
    private final static QName _GetAll_QNAME = new QName("http://SOAP.a20da10.com/", "getAll");
    private final static QName _GetByNameResponse_QNAME = new QName("http://SOAP.a20da10.com/", "getByNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.a20da10.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetByEmailResponse }
     * 
     */
    public GetByEmailResponse createGetByEmailResponse() {
        return new GetByEmailResponse();
    }

    /**
     * Create an instance of {@link GetByNameResponse }
     * 
     */
    public GetByNameResponse createGetByNameResponse() {
        return new GetByNameResponse();
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link GetByEmail }
     * 
     */
    public GetByEmail createGetByEmail() {
        return new GetByEmail();
    }

    /**
     * Create an instance of {@link GetByName }
     * 
     */
    public GetByName createGetByName() {
        return new GetByName();
    }

    /**
     * Create an instance of {@link EjbInstructorEntity }
     * 
     */
    public EjbInstructorEntity createEjbInstructorEntity() {
        return new EjbInstructorEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.a20da10.com/", name = "getByName")
    public JAXBElement<GetByName> createGetByName(GetByName value) {
        return new JAXBElement<GetByName>(_GetByName_QNAME, GetByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.a20da10.com/", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.a20da10.com/", name = "getByEmail")
    public JAXBElement<GetByEmail> createGetByEmail(GetByEmail value) {
        return new JAXBElement<GetByEmail>(_GetByEmail_QNAME, GetByEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.a20da10.com/", name = "getByEmailResponse")
    public JAXBElement<GetByEmailResponse> createGetByEmailResponse(GetByEmailResponse value) {
        return new JAXBElement<GetByEmailResponse>(_GetByEmailResponse_QNAME, GetByEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.a20da10.com/", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.a20da10.com/", name = "getByNameResponse")
    public JAXBElement<GetByNameResponse> createGetByNameResponse(GetByNameResponse value) {
        return new JAXBElement<GetByNameResponse>(_GetByNameResponse_QNAME, GetByNameResponse.class, null, value);
    }

}
