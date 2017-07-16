/**
 * UploadServlet.java
 * Created: Mar 14, 2008 10:11:20 PM
 */
package lt.igdo.servlets.images;

import java.io.File;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// TODO remove images, change images, resize images after upload.
/**
 * Servlet responsible uploading new images to server. Sample request could
 * be:<br/> <i>http://hostname/igdo-resources/upload?item=10004&id=1</i>
 * 
 * @author Donatas
 * 
 */
public class UploadServlet extends HttpServlet {

    /** Generated serialVersionUID. */
    private static final long serialVersionUID = -7870973671664795314L;

    /** Configuration bundle. It keeps images locations, sizes etc. */
    private final ResourceBundle properties = ResourceBundle
            .getBundle("images");

    /** Logger. */
    private final static Logger logger = Logger.getLogger(ImageServlet.class
            .getName());

    /**
     * Id field name.
     */
    private static final String ID_FIELD = "id";

    /**
     * Item field name.
     */
    private static final String ITEM_FIELD = "item";

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doService(req, resp);
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doService(req, resp);
    }

    /**
     * Image uploading handler method. This method is invoked for both GET and
     * POST requests.
     * 
     * @param request
     *            multipart request, with image data and parameters item and id
     *            for identifying which item which image it is. For example if
     *            item=12345 and id=2 servlet would put this image to:<br/>
     *            <i>/1/2/3/4/5/12345_02.jpg</i>
     * @param response
     *            response identifies if upload was successful.
     */
    private void doService(HttpServletRequest request,
            HttpServletResponse response) {
        boolean isMultipartContent = ServletFileUpload
                .isMultipartContent(request);
        if (!isMultipartContent) {
            UploadServlet.logger.log(Level.WARNING,
                    "Request was not multipart/form-data. Upload canceled.");
            return;
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        List<FileItem> uploadedFiles = null;
        FileItem file = null;
        try {
            uploadedFiles = fileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            try {
                response.getOutputStream().write("FAILED".getBytes());
            } catch (IOException e1) {
                UploadServlet.logger.log(Level.SEVERE,
                        "Failure reporting to client failed", e);
            }
            UploadServlet.logger.log(Level.SEVERE, "File upload failed", e);
            return;
        }
        String item = request.getParameter("item");
        String id = request.getParameter("id");
        for (FileItem fileItem : uploadedFiles) {
            if (fileItem.isFormField()) {
                String fieldName = fileItem.getFieldName();
                String fieldValue = fileItem.getString();
                if (UploadServlet.ID_FIELD.equals(fieldName)) {
                    id = fieldValue;
                } else if (UploadServlet.ITEM_FIELD.equals(fieldName)) {
                    item = fieldValue;
                }
            } else {
                file = fileItem;
            }
        }
        UploadServlet.logger.log(Level.INFO, "Request: (item=" + item + ", id="
                + id + ", file=" + file.getName() + ")");
        // All parameters are parsed
        try {
            if (item == null || id == null || item.trim().length() == 0
                    || id.trim().length() == 0) {
                throw new Exception("Required parameter is missing.");
            }
            File root = new File(properties.getString("images.location"));
            CharacterIterator it = new StringCharacterIterator(item);
            StringBuilder pathToImage = new StringBuilder(64);
            for (char character = it.first(); character != CharacterIterator.DONE; character = it
                    .next()) {
                pathToImage.append(File.separatorChar).append(character);
            }

            UploadServlet.logger.log(Level.INFO, "Looking for image under: "
                    + root + pathToImage);

            File imageFolder = new File(root.toString() + pathToImage);
            imageFolder.mkdirs();
            File image = new File(imageFolder, item + "_" + id);
            file.write(image);
        } catch (Exception e) {
            try {
                response.getOutputStream().write("FAILED".getBytes());
            } catch (IOException ex) {
                UploadServlet.logger.log(Level.SEVERE,
                        "Failure reporting to client failed", e);
            }
            UploadServlet.logger.log(Level.SEVERE, "File upload failed", e);
            return;
        }
    }
}
