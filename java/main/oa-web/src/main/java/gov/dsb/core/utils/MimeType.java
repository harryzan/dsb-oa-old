package gov.dsb.core.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * The MimeType class represents a content type of some media. It
 * stores a list of associated file/url extensions, and implements
 * static methods which can be used to identify the content type of an
 * input stream or a file.
 *
 * @author <a href="mailto:len@reeltwo.com">Len Trigg</a>
 * @version $Revision: 1.2 $
 */
public class MimeType {

    /**
     * Stores a mapping from extension to MimeType
     */
    private static HashMap<String, MimeType> EXTENSION_MAP;

    /**
     * A MimeType for html documents
     */
    public final static MimeType HTML = new MimeType("text/html", new String[]{"html", "htm"});

    /**
     * A MimeType for plaintext documents
     */
    public final static MimeType PLAINTEXT = new MimeType("text/plain", new String[]{"txt", "text", "asc"});

    /**
     * A MimeType for gif documents
     */
    public final static MimeType GIF = new MimeType("image/gif", new String[]{"gif"});

    /**
     * A MimeType for jpg documents
     */
    public final static MimeType JPG = new MimeType("image/jpeg", new String[]{"jpg", "jpeg"});

    /**
     * A MimeType for bmp documents
     */
    public final static MimeType BMP = new MimeType("image/x-xbitmap", new String[]{"bmp"});

    /**
     * A MimeType for xml documents
     */
    public final static MimeType XML = new MimeType("text/xml", new String[]{"xml"});

    /**
     * A MimeType for Associated Press News Articles
     */
    public final static MimeType AP_NEWS = new MimeType("text/x-ap-news", new String[]{"nsp", "aap"});

    /**
     * A MimeType for Adobe PDF documents
     */
    public final static MimeType PDF = new MimeType("application/pdf", new String[]{"pdf"});

    /**
     * A MimeType for Microsoft Word documents
     */
    public final static MimeType MSWORD = new MimeType("application/msword", new String[]{"doc"});

    public final static MimeType MSPPT = new MimeType("application/vnd.ms-powerpoint", new String[]{"ppt"});

    public final static MimeType MSEXCEL = new MimeType("application/vnd.ms-excel", new String[]{"xls"});

    public final static MimeType ZIP = new MimeType("application/vnd.ms-excel", new String[]{"zip"});

    /**
     * A MimeType for anything
     */
    public final static MimeType ANY = new MimeType("*/*", new String[]{});

    /**
     * Get a mapping from extensions to mimetypes
     *
     * @return A HashMap with filename extension Strings mapping to Mime-type Strings.
     */
    public static synchronized HashMap getMap() {
        if (EXTENSION_MAP == null || EXTENSION_MAP.size() == 0) {
            EXTENSION_MAP = new HashMap<String, MimeType>();
            register(HTML);
            register(PLAINTEXT);
            register(GIF);
            register(JPG);

            register(XML);
            register(PDF);
            register(MSWORD);
            register(AP_NEWS);

            register(MSPPT);
            register(ZIP);
            register(MSEXCEL);
        }
        return EXTENSION_MAP;
    }

    /**
     * Register a MimeType with the extension mapping, using the
     * MimeType's built-in list of extensions.
     *
     * @param mimetype
     */
    private static synchronized void register(MimeType mimetype) {
        String[] exts = mimetype.getExtensions();
        for (String ext : exts) {
            EXTENSION_MAP.put(ext, mimetype);
        }
    }


    /**
     * Register a specific extension with a MimeType in the extension map.
     *
     * @param extension
     * @param mimetype
     */
    private static synchronized void register(String extension, MimeType mimetype) {
        EXTENSION_MAP.put(extension, mimetype);
    }


    /**
     * Return the extension part of a url.
     *
     * @param url
     * @return
     */
    // TODO, should this handle ignoring parts of the url after "#" or "?" ?
    private static String getExtension(String url) {
        int dotpos = url.lastIndexOf('.');
        return (dotpos != -1) ?
                url.substring(dotpos + 1).trim().toLowerCase() :
                null;
    }

    /**
     * Constant to save recreating when using default contstructors
     */
    private static String[] NO_EXTS = new String[]{};

    private String mType;

    private String mSubtype;

    private String mMimetype;

    private String[] mExtensions;


    /**
     * Constructs a new MimeType.
     *
     * @param mimetype the string representation of the mimetype.
     *                 with the mimetype.
     */
    public MimeType(String mimetype) {
        this(mimetype, NO_EXTS);
    }

    /**
     * Constructs a new MimeType with associated typical file extensions.
     *
     * @param mimetype   the string representation of the mimetype.
     * @param extensions an array containing the extensions typically associated
     *                   with the mimetype.
     */
    public MimeType(String mimetype, String[] extensions) {
        if ((mimetype == null) || (extensions == null)) {
            throw new NullPointerException();
        }
        int div = mimetype.indexOf('/');
        if (div == -1) {
            throw new IllegalArgumentException("Mimetype must be of the form <type>/<subtype>");
        }
        init(mimetype.substring(0, div).toLowerCase().trim(),
                mimetype.substring(div + 1).toLowerCase().trim(),
                extensions);
    }


    /**
     * Constructs a new MimeType.
     *
     * @param mediaType    the primary media type of the mimetype.
     * @param mediaSubtype the media subtype of the mimetype.
     */
    public MimeType(String mediaType, String mediaSubtype) {
        this(mediaType, mediaSubtype, NO_EXTS);
    }

    /**
     * Constructs a new MimeType with associated typical file extensions.
     *
     * @param mediaType    the primary media type of the mimetype.
     * @param mediaSubtype the media subtype of the mimetype.
     * @param extensions   an array containing the extensions typically associated
     *                     with the mimetype.
     */
    public MimeType(String mediaType, String mediaSubtype, String[] extensions) {
        init(mediaType, mediaSubtype, extensions);
    }


    /**
     * This is the "real" constructor.
     *
     * @param mediaType
     * @param mediaSubtype
     * @param extensions
     */
    private void init(String mediaType, String mediaSubtype, String[] extensions) {
        if ((mediaType == null) || (mediaSubtype == null)) {
            throw new NullPointerException();
        }
        if ((mediaType.length() == 0) || (mediaType.indexOf('/') != -1)) {
            throw new IllegalArgumentException("Invalid media type:" + mediaType);
        }
        if ((mediaSubtype.length() == 0) || (mediaSubtype.indexOf('/') != -1)) {
            throw new IllegalArgumentException("Invalid media subtype: " + mediaSubtype);
        }
        for (String extension : extensions) {
            if (extension == null) {
                throw new NullPointerException();
            }
            else if (extension.startsWith(".")) {
                throw new IllegalArgumentException("Extension shouldn't start with '.'");
            }
        }
        mType = mediaType;
        mSubtype = mediaSubtype;
        mMimetype = new StringBuffer(mType).append('/').append(mSubtype).toString();
        mExtensions = extensions;
    }


    /**
     * Return the full mimetype string.
     *
     * @return
     */
    public String getMimeType() {
        return mMimetype;
    }


    /**
     * Return just the primary media type.
     *
     * @return
     */
    public String getMediaType() {
        return mType;
    }


    /**
     * Return the media subtype.
     *
     * @return
     */
    public String getMediaSubtype() {
        return mSubtype;
    }


    /**
     * Returns an array of extensions typically associated with the mimetype
     *
     * @return
     */
    public String[] getExtensions() {
        return mExtensions;
    }


    /**
     * Returns a human readable representation of the mimetype.
     *
     * @return
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(mMimetype);
        for (String mExtension : mExtensions) {
            sb.append(' ').append(mExtension);
        }
        return sb.toString();
    }


    /**
     * Compare an object for equality with this mimetype. Takes into
     * account only the primary type and subtype, not the list of
     * typical extensions.
     *
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof MimeType)) {
            return false;
        }
        MimeType o2 = (MimeType) o;
        return mMimetype.equals(o2.mMimetype);
    }


    /**
     * Return a hashcode for this mimetype.
     *
     * @return
     */
    public int hashCode() {
        return mMimetype.hashCode();
    }


    /**
     * Attempts to determine the mimetype of a url based solely on the name.
     *
     * @param url the url to determine the mimetype for.
     * @return the MimeType of the content, or null if unknown.
     */
    public static MimeType guessFromName(URL url) {
        if (url == null) {
            throw new NullPointerException("Null url for guesssing MimeType");
        }
        String ext = getExtension(url.getFile());
        return (ext == null) ? null : (MimeType) getMap().get(ext);
    }


    /**
     * Give a file attempts to determine the content type of the stream,
     * returning a string indicating the most likely type.
     *
     * @param f name of the file
     * @return the MimeType of the content, or null if unknown.
     */
    public static MimeType guessFromName(File f) throws Exception {
        try {
            return guessFromName(f.toURL());
        }
        catch (MalformedURLException e) {
            // Should never happen.
            throw new Exception("Converting a file to a URL gave MalformedURLException!");

        }
    }


    /**
     * For each of the supplied filenames attempts to determine the type. If no files
     * are supplied then a usage message is printed. A single or multiple files can
     * be specified.
     *
     * @param args array of filenames to determine types for.
     */
    public static void main(String[] args) throws Exception {
//    if (args.length == 0) {
//      System.out.println("USAGE: MimeType file*");
//      return;
//    }
//
//    for (int i = 0; i < args.length; i++) {
//      System.out.println(args[i] + "\t" + guessFromName(new File(args[i])));
//    }


//      System.out.println(guessFromName(new File("a.doc")));

    }

}
