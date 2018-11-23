/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

/**
 * @author Marco Girbau
 */
public class NoEsXpath 
{
    Document doc = null;
    public String EjecutaXPath(File fichero, String consulta) 
    {
        try 
        {
            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(fichero);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(fichero);
            
            //Crea el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();
            
            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(consulta);//Coge la CONSULTA
            
            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            String salida = intentoDeRecursividad(nodeList);
            //Ahora recorre la lista para sacar los resultados
//            for (int i = 0; i < nodeList.getLength(); i++) //Acceder a todos los niveles
//            {
//                salida = salida + "\n" + nodeList.item(i).getChildNodes().item(0).getNodeValue();
//            }
//            System.out.println(salida);
            return salida;
        } 
        catch (Exception ex) 
        { 
            System.out.println("Error: " + ex.toString());
            return ""; 
        }
    }
    
    public int abrir_xml (File fichero)
    {
        try 
        {
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//Se crea un objeto DocumentBuilderFactory
           factory.setIgnoringComments(true);//Para que no tenga en cuenta los comments
           factory.setIgnoringElementContentWhitespace(true);//Para que no tenga en cuenta los espacios en blanco
           DocumentBuilder builder = factory.newDocumentBuilder();//Cargamos aqui la estructura del arbol dom a partir del xml
           doc = builder.parse(fichero);
           return 0;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    public String intentoDeRecursividad(NodeList nodeList)//Me duelen los ojos
    {
        //System.out.println(nodeList.item(0).getNodeValue());
        String salida = "";
        if(nodeList != null)
        {
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                salida += intentoDeRecursividad(nodeList.item(i).getChildNodes());
                if(nodeList.item(i).getNodeValue() != null && !nodeList.item(i).getNodeValue().replace("\t", "").replace("\n", "").isEmpty())//Putas tabulaciones
                {
                    salida += nodeList.item(i).getNodeValue().replace("\t", "").replace("\n", "") + "\n";
                }
            } 
        }
        return salida;
    }
    
//    public int EjecutaXPath() 
//    {
//        try 
//        {
//            //Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            //Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
//            Document XMLDoc = factory.newDocumentBuilder().parse(new File("LibrosXML.xml"));
//            //Crea el objeto XPath
//            XPath xpath = XPathFactory.newInstance().newXPath();
//            //Crea un XPathExpression con la consulta deseada
//            XPathExpression exp = xpath.compile("/Libros/*/Autor");
//            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá el resultado como una lista de nodos.
//            Object result= exp.evaluate(XMLDoc, XPathConstants.NODESET);
//            NodeList nodeList = (NodeList) result;
//            //Ahora recorre la lista para sacar los resultados
//            for (int i = 0; i < nodeList.getLength(); i++) 
//            {
//                salida = salida + "\n" + nodeList.item(i).getChildNodes().item(0).getNodeValue();
//            }
//            System.out.println(salida);
//            return 0;
//        } 
//        catch (Exception ex) 
//        { 
//            System.out.println("Error: " + ex.toString());
//            return -1; 
//        }
//    }
}