/**
 * ImageServlet.java
 * Created: Mar 14, 2008 10:11:20 PM
 */
package lt.igdo.servlets.images;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO return different size images
/**
 * Servlet responsible for getting images from file system and streaming them to
 * application. Sample request could be:<br/>
 * <i>http://hostname/igdo-resources/image?item=10004&id=1</i>
 * 
 * @author Donatas
 * 
 */
public class ImageServlet extends HttpServlet {

    /** Generated serialVersionUID. */
    private static final long serialVersionUID = -7870973671664795314L;

    /** Configuration bundle. It keeps images locations, sizes etc. */
    private final ResourceBundle properties = ResourceBundle
            .getBundle("images");

    /** Logger. */
    private final static Logger logger = Logger.getLogger(ImageServlet.class
            .getName());

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
     * Image streaming handler method. This method is invoked for both GET and
     * POST requests.
     * 
     * @param request
     *            http request with two parameters: item and id. Item parameter
     *            is unique code which identifies which item image it is. Id
     *            parameter identifies concrete image of that item. For example
     *            if item=12345 and id=2 servlet would return this image:<br/>
     *            <i>/1/2/3/4/5/12345_02.jpg</i>
     * @param response
     *            responds with image found by request parameters. If no image
     *            found default image is shown.
     */
    protected void doService(HttpServletRequest request,
            HttpServletResponse response) {
    	ServletContext context = getServletContext();
        File root = new File(context.getRealPath(properties.getString("images.location")));
        String item = request.getParameter("item");
        String id = request.getParameter("id");
        String type = request.getParameter("type") != null ? request
                .getParameter("type") : "";

        ImageServlet.logger.log(Level.INFO, "Request: (item=" + item + ", id="
                + id + ", type=" + type + ")");

        try {
            CharacterIterator it = new StringCharacterIterator(item);
            StringBuilder pathToImage = new StringBuilder(64);
            for (char character = it.first(); character != CharacterIterator.DONE; character = it
                    .next()) {
                pathToImage.append(File.separatorChar).append(character);
            }

            ImageServlet.logger.log(Level.INFO, "Looking for image under: "
                    + root + pathToImage);

            File imageFolder = new File(root.toString() + pathToImage);
            File image = new File(imageFolder, item + type + "_" + id);
            if (!image.exists() || !image.isFile()) {
                throw new Exception("Image file not found");
            }
            writeImage(response, image);
        } catch (Exception e) {
            ImageServlet.logger.log(Level.SEVERE,
                    "Error ocurred while processing request", e);
            ImageServlet.logger.log(Level.INFO, "Returning default image.");
            writeImage(response,  new File(context.getRealPath(properties.getString("images.default"))));
        }
    }

    /**
     * Write image to response stream.
     * 
     * @param response
     *            current {@link HttpServletResponse} to which image stream is
     *            written.
     * @param image
     *            File of image we write to response.
     */
    private void writeImage(HttpServletResponse response, File image) {

        ImageServlet.logger.log(Level.INFO, "Streaming image: " + image);

        try {
            response.setContentType("image/jpg");
            OutputStream os = response.getOutputStream();
            FileInputStream fin = new FileInputStream(image);

            byte[] buf = new byte[4096];
            int count = 0;
            while (true) {
                int n = fin.read(buf);
                if (n == -1) {
                    break;
                }
                count = count + n;
                os.write(buf, 0, n);
            }
            os.flush();
            os.close();
            fin.close();
        } catch (Exception e) {
            ImageServlet.logger.log(Level.SEVERE,
                    "Error ocurred while streaming image " + image, e);
        }
    }
}
