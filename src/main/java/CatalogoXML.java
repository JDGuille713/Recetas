import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class CatalogoXML {
    private static final String RUTA_FICHERO = "src/main/resources/";
    private static final String NOMBRE_FICHERO = "recetas.xml";

    public void crearXML(ArrayList<Receta> listaRecetas) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.newDocument();

            Element recetas = documento.createElement("recetas");
            documento.appendChild(recetas);

            for (Receta recetaLista : listaRecetas) {
                Element receta = documento.createElement("receta");
                receta.setAttribute("id", String.valueOf(recetaLista.getId()));
                recetas.appendChild(receta);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(recetaLista.getNombre());
                receta.appendChild(nombre);

                Element minutosPrep =  documento.createElement("minutosPrep");
                minutosPrep.setTextContent(String.valueOf(recetaLista.getMinutosPrep()));
                receta.appendChild(minutosPrep);

                Element ingredientes =  documento.createElement("ingredientes");
                receta.appendChild(ingredientes);

                for (String ingredienteReceta : recetaLista.getIngredientes()) {
                    Element ingrediente = documento.createElement("ingrediente");
                    ingrediente.setTextContent(ingredienteReceta);
                    ingredientes.appendChild(ingrediente);
                }
            }

            Transformer transformer =  TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {}
    }
}
