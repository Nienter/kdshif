package android.support.media;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.p001v4.view.InputDeviceCompat;
import android.util.Log;
import android.util.Pair;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class ExifInterface {
    /* access modifiers changed from: private */
    public static final Charset ASCII = Charset.forName("US-ASCII");
    private static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    private static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    private static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    private static final short BYTE_ALIGN_II = 18761;
    private static final short BYTE_ALIGN_MM = 19789;
    private static final int DATA_DEFLATE_ZIP = 8;
    private static final int DATA_HUFFMAN_COMPRESSED = 2;
    private static final int DATA_JPEG = 6;
    private static final int DATA_JPEG_COMPRESSED = 7;
    private static final int DATA_LOSSY_JPEG = 34892;
    private static final int DATA_PACK_BITS_COMPRESSED = 32773;
    private static final int DATA_UNCOMPRESSED = 1;
    private static final boolean DEBUG = false;
    /* access modifiers changed from: private */
    public static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final ExifTag[] EXIF_POINTER_TAGS = {new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 1), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 1)};
    private static final ExifTag[][] EXIF_TAGS = {IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS};
    private static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ASCII);
    private static final ExifTag[] IFD_EXIF_TAGS = {new ExifTag(TAG_EXPOSURE_TIME, 33434, 5), new ExifTag(TAG_F_NUMBER, 33437, 5), new ExifTag(TAG_EXPOSURE_PROGRAM, 34850, 3), new ExifTag(TAG_SPECTRAL_SENSITIVITY, 34852, 2), new ExifTag(TAG_ISO_SPEED_RATINGS, 34855, 3), new ExifTag(TAG_OECF, 34856, 7), new ExifTag(TAG_EXIF_VERSION, 36864, 2), new ExifTag(TAG_DATETIME_ORIGINAL, 36867, 2), new ExifTag(TAG_DATETIME_DIGITIZED, 36868, 2), new ExifTag(TAG_COMPONENTS_CONFIGURATION, 37121, 7), new ExifTag(TAG_COMPRESSED_BITS_PER_PIXEL, 37122, 5), new ExifTag(TAG_SHUTTER_SPEED_VALUE, 37377, 10), new ExifTag(TAG_APERTURE_VALUE, 37378, 5), new ExifTag(TAG_BRIGHTNESS_VALUE, 37379, 10), new ExifTag(TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new ExifTag(TAG_MAX_APERTURE_VALUE, 37381, 5), new ExifTag(TAG_SUBJECT_DISTANCE, 37382, 5), new ExifTag(TAG_METERING_MODE, 37383, 3), new ExifTag(TAG_LIGHT_SOURCE, 37384, 3), new ExifTag(TAG_FLASH, 37385, 3), new ExifTag(TAG_FOCAL_LENGTH, 37386, 5), new ExifTag(TAG_SUBJECT_AREA, 37396, 3), new ExifTag(TAG_MAKER_NOTE, 37500, 7), new ExifTag(TAG_USER_COMMENT, 37510, 7), new ExifTag(TAG_SUBSEC_TIME, 37520, 2), new ExifTag(TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new ExifTag(TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new ExifTag(TAG_FLASHPIX_VERSION, 40960, 7), new ExifTag(TAG_COLOR_SPACE, 40961, 3), new ExifTag(TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new ExifTag(TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new ExifTag(TAG_RELATED_SOUND_FILE, 40964, 2), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_FLASH_ENERGY, 41483, 5), new ExifTag(TAG_SPATIAL_FREQUENCY_RESPONSE, 41484, 7), new ExifTag(TAG_FOCAL_PLANE_X_RESOLUTION, 41486, 5), new ExifTag(TAG_FOCAL_PLANE_Y_RESOLUTION, 41487, 5), new ExifTag(TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new ExifTag(TAG_SUBJECT_LOCATION, 41492, 3), new ExifTag(TAG_EXPOSURE_INDEX, 41493, 5), new ExifTag(TAG_SENSING_METHOD, 41495, 3), new ExifTag(TAG_FILE_SOURCE, 41728, 7), new ExifTag(TAG_SCENE_TYPE, 41729, 7), new ExifTag(TAG_CFA_PATTERN, 41730, 7), new ExifTag(TAG_CUSTOM_RENDERED, 41985, 3), new ExifTag(TAG_EXPOSURE_MODE, 41986, 3), new ExifTag(TAG_WHITE_BALANCE, 41987, 3), new ExifTag(TAG_DIGITAL_ZOOM_RATIO, 41988, 5), new ExifTag(TAG_FOCAL_LENGTH_IN_35MM_FILM, 41989, 3), new ExifTag(TAG_SCENE_CAPTURE_TYPE, 41990, 3), new ExifTag(TAG_GAIN_CONTROL, 41991, 3), new ExifTag(TAG_CONTRAST, 41992, 3), new ExifTag(TAG_SATURATION, 41993, 3), new ExifTag(TAG_SHARPNESS, 41994, 3), new ExifTag(TAG_DEVICE_SETTING_DESCRIPTION, 41995, 7), new ExifTag(TAG_SUBJECT_DISTANCE_RANGE, 41996, 3), new ExifTag(TAG_IMAGE_UNIQUE_ID, 42016, 2), new ExifTag(TAG_DNG_VERSION, 50706, 1), new ExifTag(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
    private static final int IFD_FORMAT_BYTE = 1;
    /* access modifiers changed from: private */
    public static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    private static final int IFD_FORMAT_DOUBLE = 12;
    private static final int IFD_FORMAT_IFD = 13;
    /* access modifiers changed from: private */
    public static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    private static final int IFD_FORMAT_SBYTE = 6;
    private static final int IFD_FORMAT_SINGLE = 11;
    private static final int IFD_FORMAT_SLONG = 9;
    private static final int IFD_FORMAT_SRATIONAL = 10;
    private static final int IFD_FORMAT_SSHORT = 8;
    private static final int IFD_FORMAT_STRING = 2;
    private static final int IFD_FORMAT_ULONG = 4;
    private static final int IFD_FORMAT_UNDEFINED = 7;
    private static final int IFD_FORMAT_URATIONAL = 5;
    private static final int IFD_FORMAT_USHORT = 3;
    private static final ExifTag[] IFD_GPS_TAGS = {new ExifTag(TAG_GPS_VERSION_ID, 0, 1), new ExifTag(TAG_GPS_LATITUDE_REF, 1, 2), new ExifTag(TAG_GPS_LATITUDE, 2, 5), new ExifTag(TAG_GPS_LONGITUDE_REF, 3, 2), new ExifTag(TAG_GPS_LONGITUDE, 4, 5), new ExifTag(TAG_GPS_ALTITUDE_REF, 5, 1), new ExifTag(TAG_GPS_ALTITUDE, 6, 5), new ExifTag(TAG_GPS_TIMESTAMP, 7, 5), new ExifTag(TAG_GPS_SATELLITES, 8, 2), new ExifTag(TAG_GPS_STATUS, 9, 2), new ExifTag(TAG_GPS_MEASURE_MODE, 10, 2), new ExifTag(TAG_GPS_DOP, 11, 5), new ExifTag(TAG_GPS_SPEED_REF, 12, 2), new ExifTag(TAG_GPS_SPEED, 13, 5), new ExifTag(TAG_GPS_TRACK_REF, 14, 2), new ExifTag(TAG_GPS_TRACK, 15, 5), new ExifTag(TAG_GPS_IMG_DIRECTION_REF, 16, 2), new ExifTag(TAG_GPS_IMG_DIRECTION, 17, 5), new ExifTag(TAG_GPS_MAP_DATUM, 18, 2), new ExifTag(TAG_GPS_DEST_LATITUDE_REF, 19, 2), new ExifTag(TAG_GPS_DEST_LATITUDE, 20, 5), new ExifTag(TAG_GPS_DEST_LONGITUDE_REF, 21, 2), new ExifTag(TAG_GPS_DEST_LONGITUDE, 22, 5), new ExifTag(TAG_GPS_DEST_BEARING_REF, 23, 2), new ExifTag(TAG_GPS_DEST_BEARING, 24, 5), new ExifTag(TAG_GPS_DEST_DISTANCE_REF, 25, 2), new ExifTag(TAG_GPS_DEST_DISTANCE, 26, 5), new ExifTag(TAG_GPS_PROCESSING_METHOD, 27, 7), new ExifTag(TAG_GPS_AREA_INFORMATION, 28, 7), new ExifTag(TAG_GPS_DATESTAMP, 29, 2), new ExifTag(TAG_GPS_DIFFERENTIAL, 30, 3)};
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = {new ExifTag(TAG_INTEROPERABILITY_INDEX, 1, 2)};
    private static final int IFD_OFFSET = 8;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS = {new ExifTag(TAG_NEW_SUBFILE_TYPE, 254, 4), new ExifTag(TAG_SUBFILE_TYPE, 255, 4), new ExifTag(TAG_THUMBNAIL_IMAGE_WIDTH, 256, 3, 4), new ExifTag(TAG_THUMBNAIL_IMAGE_LENGTH, InputDeviceCompat.SOURCE_KEYBOARD, 3, 4), new ExifTag(TAG_BITS_PER_SAMPLE, 258, 3), new ExifTag(TAG_COMPRESSION, 259, 3), new ExifTag(TAG_PHOTOMETRIC_INTERPRETATION, 262, 3), new ExifTag(TAG_IMAGE_DESCRIPTION, 270, 2), new ExifTag(TAG_MAKE, 271, 2), new ExifTag(TAG_MODEL, 272, 2), new ExifTag(TAG_STRIP_OFFSETS, 273, 3, 4), new ExifTag(TAG_ORIENTATION, 274, 3), new ExifTag(TAG_SAMPLES_PER_PIXEL, 277, 3), new ExifTag(TAG_ROWS_PER_STRIP, 278, 3, 4), new ExifTag(TAG_STRIP_BYTE_COUNTS, 279, 3, 4), new ExifTag(TAG_X_RESOLUTION, 282, 5), new ExifTag(TAG_Y_RESOLUTION, 283, 5), new ExifTag(TAG_PLANAR_CONFIGURATION, 284, 3), new ExifTag(TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(TAG_TRANSFER_FUNCTION, 301, 3), new ExifTag(TAG_SOFTWARE, 305, 2), new ExifTag(TAG_DATETIME, 306, 2), new ExifTag(TAG_ARTIST, 315, 2), new ExifTag(TAG_WHITE_POINT, 318, 5), new ExifTag(TAG_PRIMARY_CHROMATICITIES, 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, (int) InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new ExifTag(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new ExifTag(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new ExifTag(TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_REFERENCE_BLACK_WHITE, 532, 5), new ExifTag(TAG_COPYRIGHT, 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_DNG_VERSION, 50706, 1), new ExifTag(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
    private static final ExifTag[] IFD_TIFF_TAGS = {new ExifTag(TAG_NEW_SUBFILE_TYPE, 254, 4), new ExifTag(TAG_SUBFILE_TYPE, 255, 4), new ExifTag(TAG_IMAGE_WIDTH, 256, 3, 4), new ExifTag(TAG_IMAGE_LENGTH, InputDeviceCompat.SOURCE_KEYBOARD, 3, 4), new ExifTag(TAG_BITS_PER_SAMPLE, 258, 3), new ExifTag(TAG_COMPRESSION, 259, 3), new ExifTag(TAG_PHOTOMETRIC_INTERPRETATION, 262, 3), new ExifTag(TAG_IMAGE_DESCRIPTION, 270, 2), new ExifTag(TAG_MAKE, 271, 2), new ExifTag(TAG_MODEL, 272, 2), new ExifTag(TAG_STRIP_OFFSETS, 273, 3, 4), new ExifTag(TAG_ORIENTATION, 274, 3), new ExifTag(TAG_SAMPLES_PER_PIXEL, 277, 3), new ExifTag(TAG_ROWS_PER_STRIP, 278, 3, 4), new ExifTag(TAG_STRIP_BYTE_COUNTS, 279, 3, 4), new ExifTag(TAG_X_RESOLUTION, 282, 5), new ExifTag(TAG_Y_RESOLUTION, 283, 5), new ExifTag(TAG_PLANAR_CONFIGURATION, 284, 3), new ExifTag(TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(TAG_TRANSFER_FUNCTION, 301, 3), new ExifTag(TAG_SOFTWARE, 305, 2), new ExifTag(TAG_DATETIME, 306, 2), new ExifTag(TAG_ARTIST, 315, 2), new ExifTag(TAG_WHITE_POINT, 318, 5), new ExifTag(TAG_PRIMARY_CHROMATICITIES, 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, (int) InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new ExifTag(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new ExifTag(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new ExifTag(TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag(TAG_REFERENCE_BLACK_WHITE, 532, 5), new ExifTag(TAG_COPYRIGHT, 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_RW2_SENSOR_TOP_BORDER, 4, 4), new ExifTag(TAG_RW2_SENSOR_LEFT_BORDER, 5, 4), new ExifTag(TAG_RW2_SENSOR_BOTTOM_BORDER, 6, 4), new ExifTag(TAG_RW2_SENSOR_RIGHT_BORDER, 7, 4), new ExifTag(TAG_RW2_ISO, 23, 3), new ExifTag(TAG_RW2_JPG_FROM_RAW, 46, 7)};
    private static final int IFD_TYPE_EXIF = 1;
    private static final int IFD_TYPE_GPS = 2;
    private static final int IFD_TYPE_INTEROPERABILITY = 3;
    private static final int IFD_TYPE_ORF_CAMERA_SETTINGS = 7;
    private static final int IFD_TYPE_ORF_IMAGE_PROCESSING = 8;
    private static final int IFD_TYPE_ORF_MAKER_NOTE = 6;
    private static final int IFD_TYPE_PEF = 9;
    private static final int IFD_TYPE_PREVIEW = 5;
    private static final int IFD_TYPE_PRIMARY = 0;
    private static final int IFD_TYPE_THUMBNAIL = 4;
    private static final int IMAGE_TYPE_ARW = 1;
    private static final int IMAGE_TYPE_CR2 = 2;
    private static final int IMAGE_TYPE_DNG = 3;
    private static final int IMAGE_TYPE_JPEG = 4;
    private static final int IMAGE_TYPE_NEF = 5;
    private static final int IMAGE_TYPE_NRW = 6;
    private static final int IMAGE_TYPE_ORF = 7;
    private static final int IMAGE_TYPE_PEF = 8;
    private static final int IMAGE_TYPE_RAF = 9;
    private static final int IMAGE_TYPE_RW2 = 10;
    private static final int IMAGE_TYPE_SRW = 11;
    private static final int IMAGE_TYPE_UNKNOWN = 0;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag(TAG_JPEG_INTERCHANGE_FORMAT, (int) InputDeviceCompat.SOURCE_DPAD, 4);
    private static final byte[] JPEG_SIGNATURE = {MARKER, MARKER_SOI, MARKER};
    private static final byte MARKER = -1;
    private static final byte MARKER_APP1 = -31;
    private static final byte MARKER_COM = -2;
    private static final byte MARKER_EOI = -39;
    private static final byte MARKER_SOF0 = -64;
    private static final byte MARKER_SOF1 = -63;
    private static final byte MARKER_SOF10 = -54;
    private static final byte MARKER_SOF11 = -53;
    private static final byte MARKER_SOF13 = -51;
    private static final byte MARKER_SOF14 = -50;
    private static final byte MARKER_SOF15 = -49;
    private static final byte MARKER_SOF2 = -62;
    private static final byte MARKER_SOF3 = -61;
    private static final byte MARKER_SOF5 = -59;
    private static final byte MARKER_SOF6 = -58;
    private static final byte MARKER_SOF7 = -57;
    private static final byte MARKER_SOF9 = -55;
    private static final byte MARKER_SOI = -40;
    private static final byte MARKER_SOS = -38;
    private static final int MAX_THUMBNAIL_SIZE = 512;
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = {new ExifTag(TAG_ORF_PREVIEW_IMAGE_START, (int) InputDeviceCompat.SOURCE_KEYBOARD, 4), new ExifTag(TAG_ORF_PREVIEW_IMAGE_LENGTH, 258, 4)};
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = {new ExifTag(TAG_ORF_ASPECT_FRAME, 4371, 3)};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final int ORF_MAKER_NOTE_HEADER_1_SIZE = 8;
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final int ORF_MAKER_NOTE_HEADER_2_SIZE = 12;
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = {new ExifTag(TAG_ORF_THUMBNAIL_IMAGE, 256, 7), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 4), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 4)};
    private static final short ORF_SIGNATURE_1 = 20306;
    private static final short ORF_SIGNATURE_2 = 21330;
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    private static final int ORIGINAL_RESOLUTION_IMAGE = 0;
    private static final int PEF_MAKER_NOTE_SKIP_SIZE = 6;
    private static final String PEF_SIGNATURE = "PENTAX";
    private static final ExifTag[] PEF_TAGS = {new ExifTag(TAG_COLOR_SPACE, 55, 3)};
    private static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    private static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    private static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    private static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    private static final int RAF_INFO_SIZE = 160;
    private static final int RAF_JPEG_LENGTH_VALUE_SIZE = 4;
    private static final int RAF_OFFSET_TO_JPEG_IMAGE_OFFSET = 84;
    private static final String RAF_SIGNATURE = "FUJIFILMCCD-RAW";
    private static final int REDUCED_RESOLUTION_IMAGE = 1;
    private static final short RW2_SIGNATURE = 85;
    private static final int SIGNATURE_CHECK_SIZE = 5000;
    private static final byte START_CODE = 42;
    private static final String TAG = "ExifInterface";
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_COPYRIGHT = "Copyright";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_DNG_VERSION = "DNGVersion";
    private static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
    public static final String TAG_F_NUMBER = "FNumber";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
    public static final String TAG_GPS_DOP = "GPSDOP";
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    private static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";
    public static final String TAG_GPS_SPEED = "GPSSpeed";
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";
    public static final String TAG_GPS_STATUS = "GPSStatus";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_GPS_TRACK = "GPSTrack";
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";
    private static final String TAG_HAS_THUMBNAIL = "HasThumbnail";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    private static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MAKER_NOTE = "MakerNote";
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";
    public static final String TAG_OECF = "OECF";
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";
    private static final String TAG_ORF_CAMERA_SETTINGS_IFD_POINTER = "CameraSettingsIFDPointer";
    private static final String TAG_ORF_IMAGE_PROCESSING_IFD_POINTER = "ImageProcessingIFDPointer";
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag(TAG_STRIP_OFFSETS, 273, 3);
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";
    public static final String TAG_RW2_ISO = "ISO";
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";
    public static final String TAG_SUBFILE_TYPE = "SubfileType";
    public static final String TAG_SUBJECT_AREA = "SubjectArea";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";
    public static final String TAG_SUBSEC_TIME = "SubSecTime";
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    private static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    private static final String TAG_THUMBNAIL_DATA = "ThumbnailData";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    private static final String TAG_THUMBNAIL_LENGTH = "ThumbnailLength";
    private static final String TAG_THUMBNAIL_OFFSET = "ThumbnailOffset";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    public static final int WHITEBALANCE_AUTO = 0;
    public static final int WHITEBALANCE_MANUAL = 1;
    private static final HashMap sExifPointerTagMap = new HashMap();
    private static final HashMap[] sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
    private static final HashMap[] sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
    private static SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    private static final Pattern sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    private static final Pattern sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
    private static final HashSet<String> sTagSetForCompatibility = new HashSet<>(Arrays.asList(new String[]{TAG_F_NUMBER, TAG_DIGITAL_ZOOM_RATIO, TAG_EXPOSURE_TIME, TAG_SUBJECT_DISTANCE, TAG_GPS_TIMESTAMP}));
    private final AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap[] mAttributes = new HashMap[EXIF_TAGS.length];
    private ByteOrder mExifByteOrder = ByteOrder.BIG_ENDIAN;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        private InputStream mInputStream;
        /* access modifiers changed from: private */
        public final int mLength;
        /* access modifiers changed from: private */
        public int mPosition;

        public ByteOrderedDataInputStream(InputStream inputStream) {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            this.mInputStream = inputStream;
            this.mDataInputStream = new DataInputStream(inputStream);
            this.mLength = this.mDataInputStream.available();
            this.mPosition = 0;
            this.mDataInputStream.mark(this.mLength);
        }

        public ByteOrderedDataInputStream(byte[] bArr) {
            this((InputStream) new ByteArrayInputStream(bArr));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long j) {
            if (((long) this.mPosition) > j) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                j -= (long) this.mPosition;
            }
            if (skipBytes((int) j) != ((int) j)) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        public int available() {
            return this.mDataInputStream.available();
        }

        public int read() {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        public int readUnsignedByte() {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        public String readLine() {
            Log.d(ExifInterface.TAG, "Currently unsupported");
            return null;
        }

        public boolean readBoolean() {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        public char readChar() {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        public String readUTF() {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        public void readFully(byte[] bArr, int i, int i2) {
            this.mPosition += i2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, i, i2) != i2) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public void readFully(byte[] bArr) {
            this.mPosition += bArr.length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            } else if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        public byte readByte() {
            this.mPosition++;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        public short readShort() {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return (short) (read + (read2 << 8));
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (short) ((read << 8) + read2);
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public int readInt() {
            this.mPosition += 4;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return read + (read2 << 8) + (read3 << 16) + (read4 << 24);
            } else if (this.mByteOrder == BIG_ENDIAN) {
                return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
            } else {
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public int skipBytes(int i) {
            int min = Math.min(i, this.mLength - this.mPosition);
            int i2 = 0;
            while (i2 < min) {
                i2 += this.mDataInputStream.skipBytes(min - i2);
            }
            this.mPosition += i2;
            return i2;
        }

        public int readUnsignedShort() {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return read + (read2 << 8);
            } else {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public long readUnsignedInt() {
            return ((long) readInt()) & 4294967295L;
        }

        public long readLong() {
            this.mPosition += 8;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) < 0) {
                throw new EOFException();
            } else if (this.mByteOrder == LITTLE_ENDIAN) {
                return ((long) read) + (((long) read3) << 16) + (((long) read5) << 32) + (((long) read7) << 48) + (((long) read8) << 56) + (((long) read6) << 40) + (((long) read4) << 24) + (((long) read2) << 8);
            } else if (this.mByteOrder == BIG_ENDIAN) {
                return (((long) read2) << 48) + (((long) read) << 56) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
            } else {
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
        }

        public float readFloat() {
            return Float.intBitsToFloat(readInt());
        }

        public double readDouble() {
            return Double.longBitsToDouble(readLong());
        }
    }

    private static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        private final OutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.mOutputStream = outputStream;
            this.mByteOrder = byteOrder;
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void write(byte[] bArr) {
            this.mOutputStream.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) {
            this.mOutputStream.write(bArr, i, i2);
        }

        public void writeByte(int i) {
            this.mOutputStream.write(i);
        }

        public void writeShort(short s) {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((s >>> 0) & 255);
                this.mOutputStream.write((s >>> 8) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((s >>> 8) & 255);
                this.mOutputStream.write((s >>> 0) & 255);
            }
        }

        public void writeInt(int i) {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((i >>> 0) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 24) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((i >>> 24) & 255);
                this.mOutputStream.write((i >>> 16) & 255);
                this.mOutputStream.write((i >>> 8) & 255);
                this.mOutputStream.write((i >>> 0) & 255);
            }
        }

        public void writeUnsignedShort(int i) {
            writeShort((short) i);
        }

        public void writeUnsignedInt(long j) {
            writeInt((int) j);
        }
    }

    private static class ExifAttribute {
        public final byte[] bytes;
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int i, int i2, byte[] bArr) {
            this.format = i;
            this.numberOfComponents = i2;
            this.bytes = bArr;
        }

        public static ExifAttribute createUShort(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * iArr.length)]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putShort((short) i);
            }
            return new ExifAttribute(3, iArr.length, wrap.array());
        }

        public static ExifAttribute createUShort(int i, ByteOrder byteOrder) {
            return createUShort(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createULong(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * jArr.length)]);
            wrap.order(byteOrder);
            for (long j : jArr) {
                wrap.putInt((int) j);
            }
            return new ExifAttribute(4, jArr.length, wrap.array());
        }

        public static ExifAttribute createULong(long j, ByteOrder byteOrder) {
            return createULong(new long[]{j}, byteOrder);
        }

        public static ExifAttribute createSLong(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[9] * iArr.length)]);
            wrap.order(byteOrder);
            for (int putInt : iArr) {
                wrap.putInt(putInt);
            }
            return new ExifAttribute(9, iArr.length, wrap.array());
        }

        public static ExifAttribute createSLong(int i, ByteOrder byteOrder) {
            return createSLong(new int[]{i}, byteOrder);
        }

        public static ExifAttribute createByte(String str) {
            if (str.length() != 1 || str.charAt(0) < '0' || str.charAt(0) > '1') {
                byte[] bytes2 = str.getBytes(ExifInterface.ASCII);
                return new ExifAttribute(1, bytes2.length, bytes2);
            }
            byte[] bArr = {(byte) (str.charAt(0) - '0')};
            return new ExifAttribute(1, bArr.length, bArr);
        }

        public static ExifAttribute createString(String str) {
            byte[] bytes2 = (str + 0).getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, bytes2.length, bytes2);
        }

        public static ExifAttribute createURational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(5, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createURational(Rational rational, ByteOrder byteOrder) {
            return createURational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createSRational(Rational[] rationalArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[10] * rationalArr.length)]);
            wrap.order(byteOrder);
            for (Rational rational : rationalArr) {
                wrap.putInt((int) rational.numerator);
                wrap.putInt((int) rational.denominator);
            }
            return new ExifAttribute(10, rationalArr.length, wrap.array());
        }

        public static ExifAttribute createSRational(Rational rational, ByteOrder byteOrder) {
            return createSRational(new Rational[]{rational}, byteOrder);
        }

        public static ExifAttribute createDouble(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[(ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[12] * dArr.length)]);
            wrap.order(byteOrder);
            for (double putDouble : dArr) {
                wrap.putDouble(putDouble);
            }
            return new ExifAttribute(12, dArr.length, wrap.array());
        }

        public static ExifAttribute createDouble(double d, ByteOrder byteOrder) {
            return createDouble(new double[]{d}, byteOrder);
        }

        public String toString() {
            return "(" + ExifInterface.IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:164:0x021a A[SYNTHETIC, Splitter:B:164:0x021a] */
        /* JADX WARNING: Removed duplicated region for block: B:194:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x0097 A[Catch:{ IOException -> 0x00c6, all -> 0x0227 }] */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x00a3 A[SYNTHETIC, Splitter:B:48:0x00a3] */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x00d1 A[SYNTHETIC, Splitter:B:66:0x00d1] */
        public Object getValue(ByteOrder byteOrder) {
            ByteOrderedDataInputStream byteOrderedDataInputStream;
            ByteOrderedDataInputStream byteOrderedDataInputStream2;
            int i;
            boolean z = true;
            int i2 = 0;
            try {
                byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(this.bytes);
                try {
                    byteOrderedDataInputStream2.setByteOrder(byteOrder);
                    switch (this.format) {
                        case 1:
                        case 6:
                            if (this.bytes.length != 1 || this.bytes[0] < 0 || this.bytes[0] > 1) {
                                String str = new String(this.bytes, ExifInterface.ASCII);
                                if (byteOrderedDataInputStream2 == null) {
                                    return str;
                                }
                                try {
                                    byteOrderedDataInputStream2.close();
                                    return str;
                                } catch (IOException e) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e);
                                    return str;
                                }
                            } else {
                                String str2 = new String(new char[]{(char) (this.bytes[0] + 48)});
                                if (byteOrderedDataInputStream2 == null) {
                                    return str2;
                                }
                                try {
                                    byteOrderedDataInputStream2.close();
                                    return str2;
                                } catch (IOException e2) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e2);
                                    return str2;
                                }
                            }
                        case 2:
                        case 7:
                            if (this.numberOfComponents >= ExifInterface.EXIF_ASCII_PREFIX.length) {
                                int i3 = 0;
                                while (true) {
                                    if (i3 < ExifInterface.EXIF_ASCII_PREFIX.length) {
                                        if (this.bytes[i3] != ExifInterface.EXIF_ASCII_PREFIX[i3]) {
                                            z = false;
                                        } else {
                                            i3++;
                                        }
                                    }
                                }
                                if (z) {
                                    i = ExifInterface.EXIF_ASCII_PREFIX.length;
                                    StringBuilder sb = new StringBuilder();
                                    while (i < this.numberOfComponents) {
                                        byte b = this.bytes[i];
                                        if (b == 0) {
                                            String sb2 = sb.toString();
                                            if (byteOrderedDataInputStream2 == null) {
                                                return sb2;
                                            }
                                            try {
                                                byteOrderedDataInputStream2.close();
                                                return sb2;
                                            } catch (IOException e3) {
                                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e3);
                                                return sb2;
                                            }
                                        } else {
                                            if (b >= 32) {
                                                sb.append((char) b);
                                            } else {
                                                sb.append('?');
                                            }
                                            i++;
                                        }
                                    }
                                    String sb22 = sb.toString();
                                    if (byteOrderedDataInputStream2 == null) {
                                    }
                                }
                            }
                            i = 0;
                            StringBuilder sb3 = new StringBuilder();
                            while (i < this.numberOfComponents) {
                            }
                            String sb222 = sb3.toString();
                            if (byteOrderedDataInputStream2 == null) {
                            }
                        case 3:
                            int[] iArr = new int[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                iArr[i2] = byteOrderedDataInputStream2.readUnsignedShort();
                                i2++;
                            }
                            if (byteOrderedDataInputStream2 == null) {
                                return iArr;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return iArr;
                            } catch (IOException e4) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e4);
                                return iArr;
                            }
                        case 4:
                            long[] jArr = new long[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                jArr[i2] = byteOrderedDataInputStream2.readUnsignedInt();
                                i2++;
                            }
                            if (byteOrderedDataInputStream2 == null) {
                                return jArr;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return jArr;
                            } catch (IOException e5) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e5);
                                return jArr;
                            }
                        case 5:
                            Rational[] rationalArr = new Rational[this.numberOfComponents];
                            while (true) {
                                int i4 = i2;
                                if (i4 < this.numberOfComponents) {
                                    rationalArr[i4] = new Rational(byteOrderedDataInputStream2.readUnsignedInt(), byteOrderedDataInputStream2.readUnsignedInt());
                                    i2 = i4 + 1;
                                } else if (byteOrderedDataInputStream2 == null) {
                                    return rationalArr;
                                } else {
                                    try {
                                        byteOrderedDataInputStream2.close();
                                        return rationalArr;
                                    } catch (IOException e6) {
                                        Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e6);
                                        return rationalArr;
                                    }
                                }
                            }
                        case 8:
                            int[] iArr2 = new int[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                iArr2[i2] = byteOrderedDataInputStream2.readShort();
                                i2++;
                            }
                            if (byteOrderedDataInputStream2 == null) {
                                return iArr2;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return iArr2;
                            } catch (IOException e7) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e7);
                                return iArr2;
                            }
                        case 9:
                            int[] iArr3 = new int[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                iArr3[i2] = byteOrderedDataInputStream2.readInt();
                                i2++;
                            }
                            if (byteOrderedDataInputStream2 == null) {
                                return iArr3;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return iArr3;
                            } catch (IOException e8) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e8);
                                return iArr3;
                            }
                        case 10:
                            Rational[] rationalArr2 = new Rational[this.numberOfComponents];
                            while (true) {
                                int i5 = i2;
                                if (i5 < this.numberOfComponents) {
                                    rationalArr2[i5] = new Rational((long) byteOrderedDataInputStream2.readInt(), (long) byteOrderedDataInputStream2.readInt());
                                    i2 = i5 + 1;
                                } else if (byteOrderedDataInputStream2 == null) {
                                    return rationalArr2;
                                } else {
                                    try {
                                        byteOrderedDataInputStream2.close();
                                        return rationalArr2;
                                    } catch (IOException e9) {
                                        Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e9);
                                        return rationalArr2;
                                    }
                                }
                            }
                        case 11:
                            double[] dArr = new double[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                dArr[i2] = (double) byteOrderedDataInputStream2.readFloat();
                                i2++;
                            }
                            if (byteOrderedDataInputStream2 == null) {
                                return dArr;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return dArr;
                            } catch (IOException e10) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e10);
                                return dArr;
                            }
                        case 12:
                            double[] dArr2 = new double[this.numberOfComponents];
                            while (i2 < this.numberOfComponents) {
                                dArr2[i2] = byteOrderedDataInputStream2.readDouble();
                                i2++;
                            }
                            if (byteOrderedDataInputStream2 == null) {
                                return dArr2;
                            }
                            try {
                                byteOrderedDataInputStream2.close();
                                return dArr2;
                            } catch (IOException e11) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e11);
                                return dArr2;
                            }
                        default:
                            if (byteOrderedDataInputStream2 != null) {
                                try {
                                    byteOrderedDataInputStream2.close();
                                } catch (IOException e12) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e12);
                                }
                            }
                            return null;
                    }
                } catch (IOException e13) {
                    e = e13;
                    byteOrderedDataInputStream = byteOrderedDataInputStream2;
                    try {
                        Log.w(ExifInterface.TAG, "IOException occurred during reading a value", e);
                        if (byteOrderedDataInputStream != null) {
                            try {
                                byteOrderedDataInputStream.close();
                            } catch (IOException e14) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e14);
                            }
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        byteOrderedDataInputStream2 = byteOrderedDataInputStream;
                        if (byteOrderedDataInputStream2 != null) {
                            try {
                                byteOrderedDataInputStream2.close();
                            } catch (IOException e15) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e15);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (byteOrderedDataInputStream2 != null) {
                    }
                    throw th;
                }
            } catch (IOException e16) {
                e = e16;
                byteOrderedDataInputStream = null;
                Log.w(ExifInterface.TAG, "IOException occurred during reading a value", e);
                if (byteOrderedDataInputStream != null) {
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                byteOrderedDataInputStream2 = null;
                if (byteOrderedDataInputStream2 != null) {
                }
                throw th;
            }
        }

        public double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (double) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return (double) iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof double[]) {
                    double[] dArr = (double[]) value;
                    if (dArr.length == 1) {
                        return dArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof Rational[]) {
                    Rational[] rationalArr = (Rational[]) value;
                    if (rationalArr.length == 1) {
                        return rationalArr[0].calculate();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
        }

        public int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else {
                if (value instanceof long[]) {
                    long[] jArr = (long[]) value;
                    if (jArr.length == 1) {
                        return (int) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (value instanceof int[]) {
                    int[] iArr = (int[]) value;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
        }

        public String getStringValue(ByteOrder byteOrder) {
            int i = 0;
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder sb = new StringBuilder();
            if (value instanceof long[]) {
                long[] jArr = (long[]) value;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    if (i + 1 != jArr.length) {
                        sb.append(",");
                    }
                    i++;
                }
                return sb.toString();
            } else if (value instanceof int[]) {
                int[] iArr = (int[]) value;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    if (i + 1 != iArr.length) {
                        sb.append(",");
                    }
                    i++;
                }
                return sb.toString();
            } else if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    if (i + 1 != dArr.length) {
                        sb.append(",");
                    }
                    i++;
                }
                return sb.toString();
            } else if (!(value instanceof Rational[])) {
                return null;
            } else {
                Rational[] rationalArr = (Rational[]) value;
                while (i < rationalArr.length) {
                    sb.append(rationalArr[i].numerator);
                    sb.append('/');
                    sb.append(rationalArr[i].denominator);
                    if (i + 1 != rationalArr.length) {
                        sb.append(",");
                    }
                    i++;
                }
                return sb.toString();
            }
        }

        public int size() {
            return ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[this.format] * this.numberOfComponents;
        }
    }

    private static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        private ExifTag(String str, int i, int i2) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = -1;
        }

        private ExifTag(String str, int i, int i2, int i3) {
            this.name = str;
            this.number = i;
            this.primaryFormat = i2;
            this.secondaryFormat = i3;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IfdType {
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        private Rational(long j, long j2) {
            if (j2 == 0) {
                this.numerator = 0;
                this.denominator = 1;
                return;
            }
            this.numerator = j;
            this.denominator = j2;
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }

        public double calculate() {
            return ((double) this.numerator) / ((double) this.denominator);
        }
    }

    static {
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            sExifTagMapsForReading[i] = new HashMap();
            sExifTagMapsForWriting[i] = new HashMap();
            for (ExifTag exifTag : EXIF_TAGS[i]) {
                sExifTagMapsForReading[i].put(Integer.valueOf(exifTag.number), exifTag);
                sExifTagMapsForWriting[i].put(exifTag.name, exifTag);
            }
        }
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), 5);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), 1);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), 2);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), 3);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), 7);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), 8);
    }

    public ExifInterface(String str) {
        FileInputStream fileInputStream;
        if (str == null) {
            throw new IllegalArgumentException("filename cannot be null");
        }
        this.mAssetInputStream = null;
        this.mFilename = str;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                loadAttributes(fileInputStream);
                closeQuietly(fileInputStream);
            } catch (Throwable th) {
                th = th;
                closeQuietly(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            closeQuietly(fileInputStream);
            throw th;
        }
    }

    public ExifInterface(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        this.mFilename = null;
        if (inputStream instanceof AssetManager.AssetInputStream) {
            this.mAssetInputStream = (AssetManager.AssetInputStream) inputStream;
        } else {
            this.mAssetInputStream = null;
        }
        loadAttributes(inputStream);
    }

    private ExifAttribute getExifAttribute(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= EXIF_TAGS.length) {
                return null;
            }
            Object obj = this.mAttributes[i2].get(str);
            if (obj != null) {
                return (ExifAttribute) obj;
            }
            i = i2 + 1;
        }
    }

    public String getAttribute(String str) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return null;
        }
        if (!sTagSetForCompatibility.contains(str)) {
            return exifAttribute.getStringValue(this.mExifByteOrder);
        }
        if (!str.equals(TAG_GPS_TIMESTAMP)) {
            try {
                return Double.toString(exifAttribute.getDoubleValue(this.mExifByteOrder));
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (exifAttribute.format == 5 || exifAttribute.format == 10) {
            Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
            if (rationalArr == null || rationalArr.length != 3) {
                Log.w(TAG, "Invalid GPS Timestamp array. array=" + Arrays.toString(rationalArr));
                return null;
            }
            return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf((int) (((float) rationalArr[0].numerator) / ((float) rationalArr[0].denominator))), Integer.valueOf((int) (((float) rationalArr[1].numerator) / ((float) rationalArr[1].denominator))), Integer.valueOf((int) (((float) rationalArr[2].numerator) / ((float) rationalArr[2].denominator)))});
        } else {
            Log.w(TAG, "GPS Timestamp format is not rational. format=" + exifAttribute.format);
            return null;
        }
    }

    public int getAttributeInt(String str, int i) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return i;
        }
        try {
            return exifAttribute.getIntValue(this.mExifByteOrder);
        } catch (NumberFormatException e) {
            return i;
        }
    }

    public double getAttributeDouble(String str, double d) {
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute == null) {
            return d;
        }
        try {
            return exifAttribute.getDoubleValue(this.mExifByteOrder);
        } catch (NumberFormatException e) {
            return d;
        }
    }

    public void setAttribute(String str, String str2) {
        int i;
        String str3;
        if (str2 != null && sTagSetForCompatibility.contains(str)) {
            if (str.equals(TAG_GPS_TIMESTAMP)) {
                if (!sGpsTimestampPattern.matcher(str2).find()) {
                    Log.w(TAG, "Invalid value for " + str + " : " + str2);
                    return;
                }
                str2 = Integer.parseInt(r0.group(1)) + "/1," + Integer.parseInt(r0.group(2)) + "/1," + Integer.parseInt(r0.group(3)) + "/1";
            } else {
                try {
                    str2 = ((long) (Double.parseDouble(str2) * 10000.0d)) + "/10000";
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Invalid value for " + str + " : " + str2);
                    return;
                }
            }
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < EXIF_TAGS.length) {
                if (i3 != 4 || this.mHasThumbnail) {
                    Object obj = sExifTagMapsForWriting[i3].get(str);
                    if (obj != null) {
                        if (str2 != null) {
                            ExifTag exifTag = (ExifTag) obj;
                            Pair<Integer, Integer> guessDataFormat = guessDataFormat(str2);
                            if (exifTag.primaryFormat == ((Integer) guessDataFormat.first).intValue() || exifTag.primaryFormat == ((Integer) guessDataFormat.second).intValue()) {
                                i = exifTag.primaryFormat;
                            } else if (exifTag.secondaryFormat != -1 && (exifTag.secondaryFormat == ((Integer) guessDataFormat.first).intValue() || exifTag.secondaryFormat == ((Integer) guessDataFormat.second).intValue())) {
                                i = exifTag.secondaryFormat;
                            } else if (exifTag.primaryFormat == 1 || exifTag.primaryFormat == 7 || exifTag.primaryFormat == 2) {
                                i = exifTag.primaryFormat;
                            } else {
                                StringBuilder append = new StringBuilder().append("Given tag (").append(str).append(") value didn't match with one of expected ").append("formats: ").append(IFD_FORMAT_NAMES[exifTag.primaryFormat]).append(exifTag.secondaryFormat == -1 ? "" : ", " + IFD_FORMAT_NAMES[exifTag.secondaryFormat]).append(" (guess: ").append(IFD_FORMAT_NAMES[((Integer) guessDataFormat.first).intValue()]);
                                if (((Integer) guessDataFormat.second).intValue() == -1) {
                                    str3 = "";
                                } else {
                                    str3 = ", " + IFD_FORMAT_NAMES[((Integer) guessDataFormat.second).intValue()];
                                }
                                Log.w(TAG, append.append(str3).append(")").toString());
                            }
                            switch (i) {
                                case 1:
                                    this.mAttributes[i3].put(str, ExifAttribute.createByte(str2));
                                    break;
                                case 2:
                                case 7:
                                    this.mAttributes[i3].put(str, ExifAttribute.createString(str2));
                                    break;
                                case 3:
                                    String[] split = str2.split(",");
                                    int[] iArr = new int[split.length];
                                    for (int i4 = 0; i4 < split.length; i4++) {
                                        iArr[i4] = Integer.parseInt(split[i4]);
                                    }
                                    this.mAttributes[i3].put(str, ExifAttribute.createUShort(iArr, this.mExifByteOrder));
                                    break;
                                case 4:
                                    String[] split2 = str2.split(",");
                                    long[] jArr = new long[split2.length];
                                    for (int i5 = 0; i5 < split2.length; i5++) {
                                        jArr[i5] = Long.parseLong(split2[i5]);
                                    }
                                    this.mAttributes[i3].put(str, ExifAttribute.createULong(jArr, this.mExifByteOrder));
                                    break;
                                case 5:
                                    String[] split3 = str2.split(",");
                                    Rational[] rationalArr = new Rational[split3.length];
                                    for (int i6 = 0; i6 < split3.length; i6++) {
                                        String[] split4 = split3[i6].split("/");
                                        rationalArr[i6] = new Rational((long) Double.parseDouble(split4[0]), (long) Double.parseDouble(split4[1]));
                                    }
                                    this.mAttributes[i3].put(str, ExifAttribute.createURational(rationalArr, this.mExifByteOrder));
                                    break;
                                case 9:
                                    String[] split5 = str2.split(",");
                                    int[] iArr2 = new int[split5.length];
                                    for (int i7 = 0; i7 < split5.length; i7++) {
                                        iArr2[i7] = Integer.parseInt(split5[i7]);
                                    }
                                    this.mAttributes[i3].put(str, ExifAttribute.createSLong(iArr2, this.mExifByteOrder));
                                    break;
                                case 10:
                                    String[] split6 = str2.split(",");
                                    Rational[] rationalArr2 = new Rational[split6.length];
                                    for (int i8 = 0; i8 < split6.length; i8++) {
                                        String[] split7 = split6[i8].split("/");
                                        rationalArr2[i8] = new Rational((long) Double.parseDouble(split7[0]), (long) Double.parseDouble(split7[1]));
                                    }
                                    this.mAttributes[i3].put(str, ExifAttribute.createSRational(rationalArr2, this.mExifByteOrder));
                                    break;
                                case 12:
                                    String[] split8 = str2.split(",");
                                    double[] dArr = new double[split8.length];
                                    for (int i9 = 0; i9 < split8.length; i9++) {
                                        dArr[i9] = Double.parseDouble(split8[i9]);
                                    }
                                    this.mAttributes[i3].put(str, ExifAttribute.createDouble(dArr, this.mExifByteOrder));
                                    break;
                                default:
                                    Log.w(TAG, "Data format isn't one of expected formats: " + i);
                                    break;
                            }
                        } else {
                            this.mAttributes[i3].remove(str);
                        }
                    }
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    private boolean updateAttribute(String str, ExifAttribute exifAttribute) {
        boolean z = false;
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            if (this.mAttributes[i].containsKey(str)) {
                this.mAttributes[i].put(str, exifAttribute);
                z = true;
            }
        }
        return z;
    }

    private void removeAttribute(String str) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            this.mAttributes[i].remove(str);
        }
    }

    private void loadAttributes(@NonNull InputStream inputStream) {
        int i = 0;
        while (i < EXIF_TAGS.length) {
            try {
                this.mAttributes[i] = new HashMap();
                i++;
            } catch (IOException e) {
                this.mIsSupportedFile = false;
                return;
            } finally {
                addDefaultValuesForCompatibility();
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, SIGNATURE_CHECK_SIZE);
        this.mMimeType = getMimeType(bufferedInputStream);
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream((InputStream) bufferedInputStream);
        switch (this.mMimeType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
                getRawAttributes(byteOrderedDataInputStream);
                break;
            case 4:
                getJpegAttributes(byteOrderedDataInputStream, 0, 0);
                break;
            case 7:
                getOrfAttributes(byteOrderedDataInputStream);
                break;
            case 9:
                getRafAttributes(byteOrderedDataInputStream);
                break;
            case 10:
                getRw2Attributes(byteOrderedDataInputStream);
                break;
        }
        setThumbnailData(byteOrderedDataInputStream);
        this.mIsSupportedFile = true;
    }

    private void printAttributes() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.mAttributes.length) {
                Log.d(TAG, "The size of tag group[" + i2 + "]: " + this.mAttributes[i2].size());
                for (Map.Entry entry : this.mAttributes[i2].entrySet()) {
                    ExifAttribute exifAttribute = (ExifAttribute) entry.getValue();
                    Log.d(TAG, "tagName: " + entry.getKey() + ", tagType: " + exifAttribute.toString() + ", tagValue: '" + exifAttribute.getStringValue(this.mExifByteOrder) + "'");
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void saveAttributes() {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        if (!this.mIsSupportedFile || this.mMimeType != 4) {
            throw new IOException("ExifInterface only supports saving attributes on JPEG formats.");
        } else if (this.mFilename == null) {
            throw new IOException("ExifInterface does not support saving attributes for the current input.");
        } else {
            this.mThumbnailBytes = getThumbnail();
            File file = new File(this.mFilename + ".tmp");
            if (!new File(this.mFilename).renameTo(file)) {
                throw new IOException("Could not rename to " + file.getAbsolutePath());
            }
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(this.mFilename);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream2;
                    closeQuietly(fileInputStream);
                    closeQuietly(fileOutputStream);
                    file.delete();
                    throw th;
                }
                try {
                    saveJpegAttributes(fileInputStream2, fileOutputStream);
                    closeQuietly(fileInputStream2);
                    closeQuietly(fileOutputStream);
                    file.delete();
                    this.mThumbnailBytes = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    closeQuietly(fileInputStream);
                    closeQuietly(fileOutputStream);
                    file.delete();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                closeQuietly(fileInputStream);
                closeQuietly(fileOutputStream);
                file.delete();
                throw th;
            }
        }
    }

    public boolean hasThumbnail() {
        return this.mHasThumbnail;
    }

    public byte[] getThumbnail() {
        if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
            return getThumbnailBytes();
        }
        return null;
    }

    public byte[] getThumbnailBytes() {
        InputStream inputStream;
        Throwable th;
        if (!this.mHasThumbnail) {
            return null;
        }
        if (this.mThumbnailBytes != null) {
            return this.mThumbnailBytes;
        }
        try {
            if (this.mAssetInputStream != null) {
                inputStream = this.mAssetInputStream;
                try {
                    if (inputStream.markSupported()) {
                        inputStream.reset();
                    } else {
                        Log.d(TAG, "Cannot read thumbnail from inputstream without mark/reset support");
                        closeQuietly(inputStream);
                        return null;
                    }
                } catch (IOException e) {
                    e = e;
                    try {
                        Log.d(TAG, "Encountered exception while getting thumbnail", e);
                        closeQuietly(inputStream);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        closeQuietly(inputStream);
                        throw th;
                    }
                }
            } else if (this.mFilename != null) {
                inputStream = new FileInputStream(this.mFilename);
            } else {
                inputStream = null;
            }
            if (inputStream == null) {
                throw new FileNotFoundException();
            } else if (inputStream.skip((long) this.mThumbnailOffset) != ((long) this.mThumbnailOffset)) {
                throw new IOException("Corrupted image");
            } else {
                byte[] bArr = new byte[this.mThumbnailLength];
                if (inputStream.read(bArr) != this.mThumbnailLength) {
                    throw new IOException("Corrupted image");
                }
                this.mThumbnailBytes = bArr;
                closeQuietly(inputStream);
                return bArr;
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            Log.d(TAG, "Encountered exception while getting thumbnail", e);
            closeQuietly(inputStream);
            return null;
        } catch (Throwable th3) {
            inputStream = null;
            th = th3;
            closeQuietly(inputStream);
            throw th;
        }
    }

    public Bitmap getThumbnailBitmap() {
        if (!this.mHasThumbnail) {
            return null;
        }
        if (this.mThumbnailBytes == null) {
            this.mThumbnailBytes = getThumbnailBytes();
        }
        if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
            return BitmapFactory.decodeByteArray(this.mThumbnailBytes, 0, this.mThumbnailLength);
        }
        if (this.mThumbnailCompression == 1) {
            int[] iArr = new int[(this.mThumbnailBytes.length / 3)];
            for (int i = 0; i < iArr.length; i++) {
                iArr[i] = (this.mThumbnailBytes[i * 3] << 16) + 0 + (this.mThumbnailBytes[(i * 3) + 1] << 8) + this.mThumbnailBytes[(i * 3) + 2];
            }
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[4].get(TAG_IMAGE_LENGTH);
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[4].get(TAG_IMAGE_WIDTH);
            if (!(exifAttribute == null || exifAttribute2 == null)) {
                return Bitmap.createBitmap(iArr, exifAttribute2.getIntValue(this.mExifByteOrder), exifAttribute.getIntValue(this.mExifByteOrder), Bitmap.Config.ARGB_8888);
            }
        }
        return null;
    }

    public boolean isThumbnailCompressed() {
        return this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7;
    }

    public long[] getThumbnailRange() {
        if (!this.mHasThumbnail) {
            return null;
        }
        return new long[]{(long) this.mThumbnailOffset, (long) this.mThumbnailLength};
    }

    @Deprecated
    public boolean getLatLong(float[] fArr) {
        double[] latLong = getLatLong();
        if (latLong == null) {
            return false;
        }
        fArr[0] = (float) latLong[0];
        fArr[1] = (float) latLong[1];
        return true;
    }

    public double[] getLatLong() {
        String attribute = getAttribute(TAG_GPS_LATITUDE);
        String attribute2 = getAttribute(TAG_GPS_LATITUDE_REF);
        String attribute3 = getAttribute(TAG_GPS_LONGITUDE);
        String attribute4 = getAttribute(TAG_GPS_LONGITUDE_REF);
        if (!(attribute == null || attribute2 == null || attribute3 == null || attribute4 == null)) {
            try {
                return new double[]{convertRationalLatLonToDouble(attribute, attribute2), convertRationalLatLonToDouble(attribute3, attribute4)};
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Latitude/longitude values are not parseable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", new Object[]{attribute, attribute2, attribute3, attribute4}));
            }
        }
        return null;
    }

    public void setLatLong(double d, double d2) {
        if (d < -90.0d || d > 90.0d || Double.isNaN(d)) {
            throw new IllegalArgumentException("Latitude value " + d + " is not valid.");
        } else if (d2 < -180.0d || d2 > 180.0d || Double.isNaN(d2)) {
            throw new IllegalArgumentException("Longitude value " + d2 + " is not valid.");
        } else {
            setAttribute(TAG_GPS_LATITUDE_REF, d >= 0.0d ? "N" : "S");
            setAttribute(TAG_GPS_LATITUDE, convertDecimalDegree(Math.abs(d)));
            setAttribute(TAG_GPS_LONGITUDE_REF, d2 >= 0.0d ? "E" : "W");
            setAttribute(TAG_GPS_LONGITUDE, convertDecimalDegree(Math.abs(d2)));
        }
    }

    public double getAltitude(double d) {
        int i = -1;
        double attributeDouble = getAttributeDouble(TAG_GPS_ALTITUDE, -1.0d);
        int attributeInt = getAttributeInt(TAG_GPS_ALTITUDE_REF, -1);
        if (attributeDouble < 0.0d || attributeInt < 0) {
            return d;
        }
        if (attributeInt != 1) {
            i = 1;
        }
        return attributeDouble * ((double) i);
    }

    public long getDateTime() {
        String attribute = getAttribute(TAG_DATETIME);
        if (attribute == null || !sNonZeroTimePattern.matcher(attribute).matches()) {
            return -1;
        }
        try {
            Date parse = sFormatter.parse(attribute, new ParsePosition(0));
            if (parse == null) {
                return -1;
            }
            long time = parse.getTime();
            String attribute2 = getAttribute(TAG_SUBSEC_TIME);
            if (attribute2 == null) {
                return time;
            }
            try {
                long parseLong = Long.parseLong(attribute2);
                while (parseLong > 1000) {
                    parseLong /= 10;
                }
                return time + parseLong;
            } catch (NumberFormatException e) {
                return time;
            }
        } catch (IllegalArgumentException e2) {
            return -1;
        }
    }

    public long getGpsDateTime() {
        String attribute = getAttribute(TAG_GPS_DATESTAMP);
        String attribute2 = getAttribute(TAG_GPS_TIMESTAMP);
        if (attribute == null || attribute2 == null) {
            return -1;
        }
        if (!sNonZeroTimePattern.matcher(attribute).matches() && !sNonZeroTimePattern.matcher(attribute2).matches()) {
            return -1;
        }
        try {
            Date parse = sFormatter.parse(attribute + ' ' + attribute2, new ParsePosition(0));
            if (parse != null) {
                return parse.getTime();
            }
            return -1;
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    private static double convertRationalLatLonToDouble(String str, String str2) {
        try {
            String[] split = str.split(",");
            String[] split2 = split[0].split("/");
            double parseDouble = Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim());
            String[] split3 = split[1].split("/");
            double parseDouble2 = Double.parseDouble(split3[0].trim()) / Double.parseDouble(split3[1].trim());
            String[] split4 = split[2].split("/");
            double parseDouble3 = ((Double.parseDouble(split4[0].trim()) / Double.parseDouble(split4[1].trim())) / 3600.0d) + parseDouble + (parseDouble2 / 60.0d);
            if (str2.equals("S") || str2.equals("W")) {
                return -parseDouble3;
            }
            if (str2.equals("N") || str2.equals("E")) {
                return parseDouble3;
            }
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private String convertDecimalDegree(double d) {
        long j = (long) d;
        long j2 = (long) ((d - ((double) j)) * 60.0d);
        return j + "/1," + j2 + "/1," + Math.round(((d - ((double) j)) - (((double) j2) / 60.0d)) * 3600.0d * 1.0E7d) + "/10000000";
    }

    private int getMimeType(BufferedInputStream bufferedInputStream) {
        bufferedInputStream.mark(SIGNATURE_CHECK_SIZE);
        byte[] bArr = new byte[SIGNATURE_CHECK_SIZE];
        if (bufferedInputStream.read(bArr) != SIGNATURE_CHECK_SIZE) {
            throw new EOFException();
        }
        bufferedInputStream.reset();
        if (isJpegFormat(bArr)) {
            return 4;
        }
        if (isRafFormat(bArr)) {
            return 9;
        }
        if (isOrfFormat(bArr)) {
            return 7;
        }
        if (isRw2Format(bArr)) {
            return 10;
        }
        return 0;
    }

    private static boolean isJpegFormat(byte[] bArr) {
        for (int i = 0; i < JPEG_SIGNATURE.length; i++) {
            if (bArr[i] != JPEG_SIGNATURE[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isRafFormat(byte[] bArr) {
        byte[] bytes = RAF_SIGNATURE.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bArr[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrfFormat(byte[] bArr) {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
        short readShort = byteOrderedDataInputStream.readShort();
        byteOrderedDataInputStream.close();
        return readShort == 20306 || readShort == 21330;
    }

    private boolean isRw2Format(byte[] bArr) {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
        short readShort = byteOrderedDataInputStream.readShort();
        byteOrderedDataInputStream.close();
        return readShort == 85;
    }

    private void getJpegAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream, int i, int i2) {
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        byteOrderedDataInputStream.seek((long) i);
        if (byteOrderedDataInputStream.readByte() != -1) {
            throw new IOException("Invalid marker: " + Integer.toHexString(r0 & MARKER));
        }
        int i3 = i + 1;
        if (byteOrderedDataInputStream.readByte() != -40) {
            throw new IOException("Invalid marker: " + Integer.toHexString(r0 & MARKER));
        }
        int i4 = i3 + 1;
        while (true) {
            if (byteOrderedDataInputStream.readByte() != -1) {
                throw new IOException("Invalid marker:" + Integer.toHexString(r2 & MARKER));
            }
            byte readByte = byteOrderedDataInputStream.readByte();
            int i5 = i4 + 1 + 1;
            if (readByte == -39 || readByte == -38) {
                byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
            } else {
                int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort() - 2;
                int i6 = i5 + 2;
                if (readUnsignedShort < 0) {
                    throw new IOException("Invalid length");
                }
                switch (readByte) {
                    case -64:
                    case -63:
                    case -62:
                    case -61:
                    case -59:
                    case -58:
                    case -57:
                    case -55:
                    case -54:
                    case -53:
                    case -51:
                    case -50:
                    case -49:
                        if (byteOrderedDataInputStream.skipBytes(1) == 1) {
                            this.mAttributes[i2].put(TAG_IMAGE_LENGTH, ExifAttribute.createULong((long) byteOrderedDataInputStream.readUnsignedShort(), this.mExifByteOrder));
                            this.mAttributes[i2].put(TAG_IMAGE_WIDTH, ExifAttribute.createULong((long) byteOrderedDataInputStream.readUnsignedShort(), this.mExifByteOrder));
                            readUnsignedShort -= 5;
                            break;
                        } else {
                            throw new IOException("Invalid SOFx");
                        }
                    case -31:
                        if (readUnsignedShort >= 6) {
                            byte[] bArr = new byte[6];
                            if (byteOrderedDataInputStream.read(bArr) == 6) {
                                i6 += 6;
                                readUnsignedShort -= 6;
                                if (Arrays.equals(bArr, IDENTIFIER_EXIF_APP1)) {
                                    if (readUnsignedShort > 0) {
                                        this.mExifOffset = i6;
                                        byte[] bArr2 = new byte[readUnsignedShort];
                                        if (byteOrderedDataInputStream.read(bArr2) == readUnsignedShort) {
                                            readExifSegment(bArr2, i2);
                                            i6 = readUnsignedShort + i6;
                                            readUnsignedShort = 0;
                                            break;
                                        } else {
                                            throw new IOException("Invalid exif");
                                        }
                                    } else {
                                        throw new IOException("Invalid exif");
                                    }
                                }
                            } else {
                                throw new IOException("Invalid exif");
                            }
                        }
                        break;
                    case -2:
                        byte[] bArr3 = new byte[readUnsignedShort];
                        if (byteOrderedDataInputStream.read(bArr3) == readUnsignedShort) {
                            if (getAttribute(TAG_USER_COMMENT) != null) {
                                readUnsignedShort = 0;
                                break;
                            } else {
                                this.mAttributes[1].put(TAG_USER_COMMENT, ExifAttribute.createString(new String(bArr3, ASCII)));
                                readUnsignedShort = 0;
                                break;
                            }
                        } else {
                            throw new IOException("Invalid exif");
                        }
                }
                if (readUnsignedShort < 0) {
                    throw new IOException("Invalid length");
                } else if (byteOrderedDataInputStream.skipBytes(readUnsignedShort) != readUnsignedShort) {
                    throw new IOException("Invalid JPEG segment");
                } else {
                    i4 = readUnsignedShort + i6;
                }
            }
        }
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
    }

    private void getRawAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        parseTiffHeaders(byteOrderedDataInputStream, byteOrderedDataInputStream.available());
        readImageFileDirectory(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 0);
        updateImageSizeValues(byteOrderedDataInputStream, 5);
        updateImageSizeValues(byteOrderedDataInputStream, 4);
        validateImages(byteOrderedDataInputStream);
        if (this.mMimeType == 8) {
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get(TAG_MAKER_NOTE);
            if (exifAttribute != null) {
                ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
                byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
                byteOrderedDataInputStream2.seek(6);
                readImageFileDirectory(byteOrderedDataInputStream2, 9);
                ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[9].get(TAG_COLOR_SPACE);
                if (exifAttribute2 != null) {
                    this.mAttributes[1].put(TAG_COLOR_SPACE, exifAttribute2);
                }
            }
        }
    }

    private void getRafAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        byteOrderedDataInputStream.skipBytes(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byteOrderedDataInputStream.read(bArr);
        byteOrderedDataInputStream.skipBytes(4);
        byteOrderedDataInputStream.read(bArr2);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        getJpegAttributes(byteOrderedDataInputStream, i, 5);
        byteOrderedDataInputStream.seek((long) i2);
        byteOrderedDataInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        int readInt = byteOrderedDataInputStream.readInt();
        for (int i3 = 0; i3 < readInt; i3++) {
            int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
            int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
            if (readUnsignedShort == TAG_RAF_IMAGE_SIZE.number) {
                short readShort = byteOrderedDataInputStream.readShort();
                short readShort2 = byteOrderedDataInputStream.readShort();
                ExifAttribute createUShort = ExifAttribute.createUShort((int) readShort, this.mExifByteOrder);
                ExifAttribute createUShort2 = ExifAttribute.createUShort((int) readShort2, this.mExifByteOrder);
                this.mAttributes[0].put(TAG_IMAGE_LENGTH, createUShort);
                this.mAttributes[0].put(TAG_IMAGE_WIDTH, createUShort2);
                return;
            }
            byteOrderedDataInputStream.skipBytes(readUnsignedShort2);
        }
    }

    private void getOrfAttributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        getRawAttributes(byteOrderedDataInputStream);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get(TAG_MAKER_NOTE);
        if (exifAttribute != null) {
            ByteOrderedDataInputStream byteOrderedDataInputStream2 = new ByteOrderedDataInputStream(exifAttribute.bytes);
            byteOrderedDataInputStream2.setByteOrder(this.mExifByteOrder);
            byte[] bArr = new byte[ORF_MAKER_NOTE_HEADER_1.length];
            byteOrderedDataInputStream2.readFully(bArr);
            byteOrderedDataInputStream2.seek(0);
            byte[] bArr2 = new byte[ORF_MAKER_NOTE_HEADER_2.length];
            byteOrderedDataInputStream2.readFully(bArr2);
            if (Arrays.equals(bArr, ORF_MAKER_NOTE_HEADER_1)) {
                byteOrderedDataInputStream2.seek(8);
            } else if (Arrays.equals(bArr2, ORF_MAKER_NOTE_HEADER_2)) {
                byteOrderedDataInputStream2.seek(12);
            }
            readImageFileDirectory(byteOrderedDataInputStream2, 6);
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[7].get(TAG_ORF_PREVIEW_IMAGE_START);
            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[7].get(TAG_ORF_PREVIEW_IMAGE_LENGTH);
            if (!(exifAttribute2 == null || exifAttribute3 == null)) {
                this.mAttributes[5].put(TAG_JPEG_INTERCHANGE_FORMAT, exifAttribute2);
                this.mAttributes[5].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, exifAttribute3);
            }
            ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[8].get(TAG_ORF_ASPECT_FRAME);
            if (exifAttribute4 != null) {
                int[] iArr = (int[]) exifAttribute4.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 4) {
                    Log.w(TAG, "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
                } else if (iArr[2] > iArr[0] && iArr[3] > iArr[1]) {
                    int i = (iArr[2] - iArr[0]) + 1;
                    int i2 = (iArr[3] - iArr[1]) + 1;
                    if (i < i2) {
                        int i3 = i + i2;
                        i2 = i3 - i2;
                        i = i3 - i2;
                    }
                    ExifAttribute createUShort = ExifAttribute.createUShort(i, this.mExifByteOrder);
                    ExifAttribute createUShort2 = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                    this.mAttributes[0].put(TAG_IMAGE_WIDTH, createUShort);
                    this.mAttributes[0].put(TAG_IMAGE_LENGTH, createUShort2);
                }
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        getRawAttributes(byteOrderedDataInputStream);
        if (((ExifAttribute) this.mAttributes[0].get(TAG_RW2_JPG_FROM_RAW)) != null) {
            getJpegAttributes(byteOrderedDataInputStream, this.mRw2JpgFromRawOffset, 5);
        }
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[0].get(TAG_RW2_ISO);
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get(TAG_ISO_SPEED_RATINGS);
        if (exifAttribute != null && exifAttribute2 == null) {
            this.mAttributes[1].put(TAG_ISO_SPEED_RATINGS, exifAttribute);
        }
    }

    private void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        if (dataInputStream.readByte() != -1) {
            throw new IOException("Invalid marker");
        }
        byteOrderedDataOutputStream.writeByte(-1);
        if (dataInputStream.readByte() != -40) {
            throw new IOException("Invalid marker");
        }
        byteOrderedDataOutputStream.writeByte(-40);
        byteOrderedDataOutputStream.writeByte(-1);
        byteOrderedDataOutputStream.writeByte(-31);
        writeExifSegment(byteOrderedDataOutputStream, 6);
        byte[] bArr = new byte[4096];
        while (dataInputStream.readByte() == -1) {
            byte readByte = dataInputStream.readByte();
            switch (readByte) {
                case -39:
                case -38:
                    byteOrderedDataOutputStream.writeByte(-1);
                    byteOrderedDataOutputStream.writeByte(readByte);
                    copy(dataInputStream, byteOrderedDataOutputStream);
                    return;
                case -31:
                    int readUnsignedShort = dataInputStream.readUnsignedShort() - 2;
                    if (readUnsignedShort >= 0) {
                        byte[] bArr2 = new byte[6];
                        if (readUnsignedShort >= 6) {
                            if (dataInputStream.read(bArr2) == 6) {
                                if (Arrays.equals(bArr2, IDENTIFIER_EXIF_APP1)) {
                                    if (dataInputStream.skipBytes(readUnsignedShort - 6) == readUnsignedShort - 6) {
                                        break;
                                    } else {
                                        throw new IOException("Invalid length");
                                    }
                                }
                            } else {
                                throw new IOException("Invalid exif");
                            }
                        }
                        byteOrderedDataOutputStream.writeByte(-1);
                        byteOrderedDataOutputStream.writeByte(readByte);
                        byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort + 2);
                        if (readUnsignedShort >= 6) {
                            readUnsignedShort -= 6;
                            byteOrderedDataOutputStream.write(bArr2);
                        }
                        while (readUnsignedShort > 0) {
                            int read = dataInputStream.read(bArr, 0, Math.min(readUnsignedShort, bArr.length));
                            if (read < 0) {
                                break;
                            } else {
                                byteOrderedDataOutputStream.write(bArr, 0, read);
                                readUnsignedShort -= read;
                            }
                        }
                        break;
                    } else {
                        throw new IOException("Invalid length");
                    }
                default:
                    byteOrderedDataOutputStream.writeByte(-1);
                    byteOrderedDataOutputStream.writeByte(readByte);
                    int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                    byteOrderedDataOutputStream.writeUnsignedShort(readUnsignedShort2);
                    int i = readUnsignedShort2 - 2;
                    if (i >= 0) {
                        while (i > 0) {
                            int read2 = dataInputStream.read(bArr, 0, Math.min(i, bArr.length));
                            if (read2 < 0) {
                                break;
                            } else {
                                byteOrderedDataOutputStream.write(bArr, 0, read2);
                                i -= read2;
                            }
                        }
                        break;
                    } else {
                        throw new IOException("Invalid length");
                    }
            }
        }
        throw new IOException("Invalid marker");
    }

    private void readExifSegment(byte[] bArr, int i) {
        ByteOrderedDataInputStream byteOrderedDataInputStream = new ByteOrderedDataInputStream(bArr);
        parseTiffHeaders(byteOrderedDataInputStream, bArr.length);
        readImageFileDirectory(byteOrderedDataInputStream, i);
    }

    private void addDefaultValuesForCompatibility() {
        String attribute = getAttribute(TAG_DATETIME_ORIGINAL);
        if (attribute != null) {
            this.mAttributes[0].put(TAG_DATETIME, ExifAttribute.createString(attribute));
        }
        if (getAttribute(TAG_IMAGE_WIDTH) == null) {
            this.mAttributes[0].put(TAG_IMAGE_WIDTH, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute(TAG_IMAGE_LENGTH) == null) {
            this.mAttributes[0].put(TAG_IMAGE_LENGTH, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute(TAG_ORIENTATION) == null) {
            this.mAttributes[0].put(TAG_ORIENTATION, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (getAttribute(TAG_LIGHT_SOURCE) == null) {
            this.mAttributes[1].put(TAG_LIGHT_SOURCE, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        short readShort = byteOrderedDataInputStream.readShort();
        switch (readShort) {
            case 18761:
                return ByteOrder.LITTLE_ENDIAN;
            case 19789:
                return ByteOrder.BIG_ENDIAN;
            default:
                throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
        }
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) {
        this.mExifByteOrder = readByteOrder(byteOrderedDataInputStream);
        byteOrderedDataInputStream.setByteOrder(this.mExifByteOrder);
        int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
        if (this.mMimeType == 7 || this.mMimeType == 10 || readUnsignedShort == 42) {
            int readInt = byteOrderedDataInputStream.readInt();
            if (readInt < 8 || readInt >= i) {
                throw new IOException("Invalid first Ifd offset: " + readInt);
            }
            int i2 = readInt - 8;
            if (i2 > 0 && byteOrderedDataInputStream.skipBytes(i2) != i2) {
                throw new IOException("Couldn't jump to first Ifd: " + i2);
            }
            return;
        }
        throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
    }

    private void readImageFileDirectory(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) {
        if (byteOrderedDataInputStream.mPosition + 2 <= byteOrderedDataInputStream.mLength) {
            short readShort = byteOrderedDataInputStream.readShort();
            if (byteOrderedDataInputStream.mPosition + (readShort * 12) <= byteOrderedDataInputStream.mLength) {
                for (short s = 0; s < readShort; s = (short) (s + 1)) {
                    int readUnsignedShort = byteOrderedDataInputStream.readUnsignedShort();
                    int readUnsignedShort2 = byteOrderedDataInputStream.readUnsignedShort();
                    int readInt = byteOrderedDataInputStream.readInt();
                    long peek = (long) (byteOrderedDataInputStream.peek() + 4);
                    ExifTag exifTag = (ExifTag) sExifTagMapsForReading[i].get(Integer.valueOf(readUnsignedShort));
                    long j = 0;
                    boolean z = false;
                    if (exifTag == null) {
                        Log.w(TAG, "Skip the tag entry since tag number is not defined: " + readUnsignedShort);
                    } else if (readUnsignedShort2 <= 0 || readUnsignedShort2 >= IFD_FORMAT_BYTES_PER_FORMAT.length) {
                        Log.w(TAG, "Skip the tag entry since data format is invalid: " + readUnsignedShort2);
                    } else {
                        j = ((long) readInt) * ((long) IFD_FORMAT_BYTES_PER_FORMAT[readUnsignedShort2]);
                        if (j < 0 || j > 2147483647L) {
                            Log.w(TAG, "Skip the tag entry since the number of components is invalid: " + readInt);
                        } else {
                            z = true;
                        }
                    }
                    if (!z) {
                        byteOrderedDataInputStream.seek(peek);
                    } else {
                        if (j > 4) {
                            int readInt2 = byteOrderedDataInputStream.readInt();
                            if (this.mMimeType == 7) {
                                if (exifTag.name == TAG_MAKER_NOTE) {
                                    this.mOrfMakerNoteOffset = readInt2;
                                } else if (i == 6 && exifTag.name == TAG_ORF_THUMBNAIL_IMAGE) {
                                    this.mOrfThumbnailOffset = readInt2;
                                    this.mOrfThumbnailLength = readInt;
                                    ExifAttribute createUShort = ExifAttribute.createUShort(6, this.mExifByteOrder);
                                    ExifAttribute createULong = ExifAttribute.createULong((long) this.mOrfThumbnailOffset, this.mExifByteOrder);
                                    ExifAttribute createULong2 = ExifAttribute.createULong((long) this.mOrfThumbnailLength, this.mExifByteOrder);
                                    this.mAttributes[4].put(TAG_COMPRESSION, createUShort);
                                    this.mAttributes[4].put(TAG_JPEG_INTERCHANGE_FORMAT, createULong);
                                    this.mAttributes[4].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, createULong2);
                                }
                            } else if (this.mMimeType == 10 && exifTag.name == TAG_RW2_JPG_FROM_RAW) {
                                this.mRw2JpgFromRawOffset = readInt2;
                            }
                            if (((long) readInt2) + j <= ((long) byteOrderedDataInputStream.mLength)) {
                                byteOrderedDataInputStream.seek((long) readInt2);
                            } else {
                                Log.w(TAG, "Skip the tag entry since data offset is invalid: " + readInt2);
                                byteOrderedDataInputStream.seek(peek);
                            }
                        }
                        Object obj = sExifPointerTagMap.get(Integer.valueOf(readUnsignedShort));
                        if (obj != null) {
                            long j2 = -1;
                            switch (readUnsignedShort2) {
                                case 3:
                                    j2 = (long) byteOrderedDataInputStream.readUnsignedShort();
                                    break;
                                case 4:
                                    j2 = byteOrderedDataInputStream.readUnsignedInt();
                                    break;
                                case 8:
                                    j2 = (long) byteOrderedDataInputStream.readShort();
                                    break;
                                case 9:
                                case 13:
                                    j2 = (long) byteOrderedDataInputStream.readInt();
                                    break;
                            }
                            if (j2 <= 0 || j2 >= ((long) byteOrderedDataInputStream.mLength)) {
                                Log.w(TAG, "Skip jump into the IFD since its offset is invalid: " + j2);
                            } else {
                                byteOrderedDataInputStream.seek(j2);
                                readImageFileDirectory(byteOrderedDataInputStream, ((Integer) obj).intValue());
                            }
                            byteOrderedDataInputStream.seek(peek);
                        } else {
                            byte[] bArr = new byte[((int) j)];
                            byteOrderedDataInputStream.readFully(bArr);
                            ExifAttribute exifAttribute = new ExifAttribute(readUnsignedShort2, readInt, bArr);
                            this.mAttributes[i].put(exifTag.name, exifAttribute);
                            if (exifTag.name == TAG_DNG_VERSION) {
                                this.mMimeType = 3;
                            }
                            if (((exifTag.name == TAG_MAKE || exifTag.name == TAG_MODEL) && exifAttribute.getStringValue(this.mExifByteOrder).contains(PEF_SIGNATURE)) || (exifTag.name == TAG_COMPRESSION && exifAttribute.getIntValue(this.mExifByteOrder) == 65535)) {
                                this.mMimeType = 8;
                            }
                            if (((long) byteOrderedDataInputStream.peek()) != peek) {
                                byteOrderedDataInputStream.seek(peek);
                            }
                        }
                    }
                }
                if (byteOrderedDataInputStream.peek() + 4 <= byteOrderedDataInputStream.mLength) {
                    int readInt3 = byteOrderedDataInputStream.readInt();
                    if (readInt3 > 8 && readInt3 < byteOrderedDataInputStream.mLength) {
                        byteOrderedDataInputStream.seek((long) readInt3);
                        if (this.mAttributes[4].isEmpty()) {
                            readImageFileDirectory(byteOrderedDataInputStream, 4);
                        } else if (this.mAttributes[5].isEmpty()) {
                            readImageFileDirectory(byteOrderedDataInputStream, 5);
                        }
                    }
                }
            }
        }
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) {
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_LENGTH);
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_WIDTH);
        if (exifAttribute == null || exifAttribute2 == null) {
            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i].get(TAG_JPEG_INTERCHANGE_FORMAT);
            if (exifAttribute3 != null) {
                getJpegAttributes(byteOrderedDataInputStream, exifAttribute3.getIntValue(this.mExifByteOrder), i);
            }
        }
    }

    private void setThumbnailData(ByteOrderedDataInputStream byteOrderedDataInputStream) {
        HashMap hashMap = this.mAttributes[4];
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_COMPRESSION);
        if (exifAttribute != null) {
            this.mThumbnailCompression = exifAttribute.getIntValue(this.mExifByteOrder);
            switch (this.mThumbnailCompression) {
                case 1:
                case 7:
                    if (isSupportedDataType(hashMap)) {
                        handleThumbnailFromStrips(byteOrderedDataInputStream, hashMap);
                        return;
                    }
                    return;
                case 6:
                    handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
                    return;
                default:
                    return;
            }
        } else {
            this.mThumbnailCompression = 6;
            handleThumbnailFromJfif(byteOrderedDataInputStream, hashMap);
        }
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT);
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (exifAttribute != null && exifAttribute2 != null) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            int min = Math.min(exifAttribute2.getIntValue(this.mExifByteOrder), byteOrderedDataInputStream.available() - intValue);
            if (this.mMimeType == 4 || this.mMimeType == 9 || this.mMimeType == 10) {
                intValue += this.mExifOffset;
            } else if (this.mMimeType == 7) {
                intValue += this.mOrfMakerNoteOffset;
            }
            if (intValue > 0 && min > 0) {
                this.mHasThumbnail = true;
                this.mThumbnailOffset = intValue;
                this.mThumbnailLength = min;
                if (this.mFilename == null && this.mAssetInputStream == null) {
                    byte[] bArr = new byte[min];
                    byteOrderedDataInputStream.seek((long) intValue);
                    byteOrderedDataInputStream.readFully(bArr);
                    this.mThumbnailBytes = bArr;
                }
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream byteOrderedDataInputStream, HashMap hashMap) {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_STRIP_OFFSETS);
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_STRIP_BYTE_COUNTS);
        if (exifAttribute != null && exifAttribute2 != null) {
            long[] convertToLongArray = convertToLongArray(exifAttribute.getValue(this.mExifByteOrder));
            long[] convertToLongArray2 = convertToLongArray(exifAttribute2.getValue(this.mExifByteOrder));
            if (convertToLongArray == null) {
                Log.w(TAG, "stripOffsets should not be null.");
            } else if (convertToLongArray2 == null) {
                Log.w(TAG, "stripByteCounts should not be null.");
            } else {
                long j = 0;
                for (long j2 : convertToLongArray2) {
                    j += j2;
                }
                byte[] bArr = new byte[((int) j)];
                int i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < convertToLongArray.length; i3++) {
                    int i4 = (int) convertToLongArray[i3];
                    int i5 = (int) convertToLongArray2[i3];
                    int i6 = i4 - i2;
                    if (i6 < 0) {
                        Log.d(TAG, "Invalid strip offset value");
                    }
                    byteOrderedDataInputStream.seek((long) i6);
                    int i7 = i2 + i6;
                    byte[] bArr2 = new byte[i5];
                    byteOrderedDataInputStream.read(bArr2);
                    i2 = i7 + i5;
                    System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
                    i += bArr2.length;
                }
                this.mHasThumbnail = true;
                this.mThumbnailBytes = bArr;
                this.mThumbnailLength = bArr.length;
            }
        }
    }

    private boolean isSupportedDataType(HashMap hashMap) {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_BITS_PER_SAMPLE);
        if (exifAttribute != null) {
            int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
            if (Arrays.equals(BITS_PER_SAMPLE_RGB, iArr)) {
                return true;
            }
            if (this.mMimeType == 3) {
                ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_PHOTOMETRIC_INTERPRETATION);
                if (exifAttribute2 != null) {
                    int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
                    if ((intValue == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (intValue == 6 && Arrays.equals(iArr, BITS_PER_SAMPLE_RGB))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isThumbnail(HashMap hashMap) {
        ExifAttribute exifAttribute = (ExifAttribute) hashMap.get(TAG_IMAGE_LENGTH);
        ExifAttribute exifAttribute2 = (ExifAttribute) hashMap.get(TAG_IMAGE_WIDTH);
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
            if (intValue <= 512 && intValue2 <= 512) {
                return true;
            }
        }
        return false;
    }

    private void validateImages(InputStream inputStream) {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[1].get(TAG_PIXEL_X_DIMENSION);
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[1].get(TAG_PIXEL_Y_DIMENSION);
        if (!(exifAttribute == null || exifAttribute2 == null)) {
            this.mAttributes[0].put(TAG_IMAGE_WIDTH, exifAttribute);
            this.mAttributes[0].put(TAG_IMAGE_LENGTH, exifAttribute2);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            this.mAttributes[4] = this.mAttributes[5];
            this.mAttributes[5] = new HashMap();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d(TAG, "No image meets the size requirements of a thumbnail image.");
        }
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream byteOrderedDataInputStream, int i) {
        ExifAttribute createUShort;
        ExifAttribute createUShort2;
        ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(TAG_DEFAULT_CROP_SIZE);
        ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_TOP_BORDER);
        ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_LEFT_BORDER);
        ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_BOTTOM_BORDER);
        ExifAttribute exifAttribute5 = (ExifAttribute) this.mAttributes[i].get(TAG_RW2_SENSOR_RIGHT_BORDER);
        if (exifAttribute != null) {
            if (exifAttribute.format == 5) {
                Rational[] rationalArr = (Rational[]) exifAttribute.getValue(this.mExifByteOrder);
                if (rationalArr == null || rationalArr.length != 2) {
                    Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(rationalArr));
                    return;
                } else {
                    createUShort = ExifAttribute.createURational(rationalArr[0], this.mExifByteOrder);
                    createUShort2 = ExifAttribute.createURational(rationalArr[1], this.mExifByteOrder);
                }
            } else {
                int[] iArr = (int[]) exifAttribute.getValue(this.mExifByteOrder);
                if (iArr == null || iArr.length != 2) {
                    Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                    return;
                } else {
                    createUShort = ExifAttribute.createUShort(iArr[0], this.mExifByteOrder);
                    createUShort2 = ExifAttribute.createUShort(iArr[1], this.mExifByteOrder);
                }
            }
            this.mAttributes[i].put(TAG_IMAGE_WIDTH, createUShort);
            this.mAttributes[i].put(TAG_IMAGE_LENGTH, createUShort2);
        } else if (exifAttribute2 == null || exifAttribute3 == null || exifAttribute4 == null || exifAttribute5 == null) {
            retrieveJpegImageSize(byteOrderedDataInputStream, i);
        } else {
            int intValue = exifAttribute2.getIntValue(this.mExifByteOrder);
            int intValue2 = exifAttribute4.getIntValue(this.mExifByteOrder);
            int intValue3 = exifAttribute5.getIntValue(this.mExifByteOrder);
            int intValue4 = exifAttribute3.getIntValue(this.mExifByteOrder);
            if (intValue2 > intValue && intValue3 > intValue4) {
                int i2 = intValue2 - intValue;
                ExifAttribute createUShort3 = ExifAttribute.createUShort(i2, this.mExifByteOrder);
                ExifAttribute createUShort4 = ExifAttribute.createUShort(intValue3 - intValue4, this.mExifByteOrder);
                this.mAttributes[i].put(TAG_IMAGE_LENGTH, createUShort3);
                this.mAttributes[i].put(TAG_IMAGE_WIDTH, createUShort4);
            }
        }
    }

    private int writeExifSegment(ByteOrderedDataOutputStream byteOrderedDataOutputStream, int i) {
        int i2;
        int[] iArr = new int[EXIF_TAGS.length];
        int[] iArr2 = new int[EXIF_TAGS.length];
        for (ExifTag exifTag : EXIF_POINTER_TAGS) {
            removeAttribute(exifTag.name);
        }
        removeAttribute(JPEG_INTERCHANGE_FORMAT_TAG.name);
        removeAttribute(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= EXIF_TAGS.length) {
                break;
            }
            for (Object obj : this.mAttributes[i4].entrySet().toArray()) {
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getValue() == null) {
                    this.mAttributes[i4].remove(entry.getKey());
                }
            }
            i3 = i4 + 1;
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0, this.mExifByteOrder));
        }
        if (this.mHasThumbnail) {
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(0, this.mExifByteOrder));
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name, ExifAttribute.createULong((long) this.mThumbnailLength, this.mExifByteOrder));
        }
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= EXIF_TAGS.length) {
                break;
            }
            int i7 = 0;
            Iterator it = this.mAttributes[i6].entrySet().iterator();
            while (true) {
                i2 = i7;
                if (!it.hasNext()) {
                    break;
                }
                int size = ((ExifAttribute) ((Map.Entry) it.next()).getValue()).size();
                if (size > 4) {
                    i7 = size + i2;
                } else {
                    i7 = i2;
                }
            }
            iArr2[i6] = iArr2[i6] + i2;
            i5 = i6 + 1;
        }
        int i8 = 8;
        for (int i9 = 0; i9 < EXIF_TAGS.length; i9++) {
            if (!this.mAttributes[i9].isEmpty()) {
                iArr[i9] = i8;
                i8 += (this.mAttributes[i9].size() * 12) + 2 + 4 + iArr2[i9];
            }
        }
        if (this.mHasThumbnail) {
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong((long) i8, this.mExifByteOrder));
            this.mThumbnailOffset = i + i8;
            i8 += this.mThumbnailLength;
        }
        int i10 = i8 + 8;
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong((long) iArr[1], this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong((long) iArr[2], this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong((long) iArr[3], this.mExifByteOrder));
        }
        byteOrderedDataOutputStream.writeUnsignedShort(i10);
        byteOrderedDataOutputStream.write(IDENTIFIER_EXIF_APP1);
        byteOrderedDataOutputStream.writeShort(this.mExifByteOrder == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
        byteOrderedDataOutputStream.setByteOrder(this.mExifByteOrder);
        byteOrderedDataOutputStream.writeUnsignedShort(42);
        byteOrderedDataOutputStream.writeUnsignedInt(8);
        int i11 = 0;
        while (true) {
            int i12 = i11;
            if (i12 >= EXIF_TAGS.length) {
                break;
            }
            if (!this.mAttributes[i12].isEmpty()) {
                byteOrderedDataOutputStream.writeUnsignedShort(this.mAttributes[i12].size());
                int size2 = iArr[i12] + 2 + (this.mAttributes[i12].size() * 12) + 4;
                Iterator it2 = this.mAttributes[i12].entrySet().iterator();
                while (true) {
                    int i13 = size2;
                    if (!it2.hasNext()) {
                        break;
                    }
                    Map.Entry entry2 = (Map.Entry) it2.next();
                    int i14 = ((ExifTag) sExifTagMapsForWriting[i12].get(entry2.getKey())).number;
                    ExifAttribute exifAttribute = (ExifAttribute) entry2.getValue();
                    int size3 = exifAttribute.size();
                    byteOrderedDataOutputStream.writeUnsignedShort(i14);
                    byteOrderedDataOutputStream.writeUnsignedShort(exifAttribute.format);
                    byteOrderedDataOutputStream.writeInt(exifAttribute.numberOfComponents);
                    if (size3 > 4) {
                        byteOrderedDataOutputStream.writeUnsignedInt((long) i13);
                        i13 += size3;
                    } else {
                        byteOrderedDataOutputStream.write(exifAttribute.bytes);
                        if (size3 < 4) {
                            for (int i15 = size3; i15 < 4; i15++) {
                                byteOrderedDataOutputStream.writeByte(0);
                            }
                        }
                    }
                    size2 = i13;
                }
                if (i12 != 0 || this.mAttributes[4].isEmpty()) {
                    byteOrderedDataOutputStream.writeUnsignedInt(0);
                } else {
                    byteOrderedDataOutputStream.writeUnsignedInt((long) iArr[4]);
                }
                for (Map.Entry value : this.mAttributes[i12].entrySet()) {
                    ExifAttribute exifAttribute2 = (ExifAttribute) value.getValue();
                    if (exifAttribute2.bytes.length > 4) {
                        byteOrderedDataOutputStream.write(exifAttribute2.bytes, 0, exifAttribute2.bytes.length);
                    }
                }
            }
            i11 = i12 + 1;
        }
        if (this.mHasThumbnail) {
            byteOrderedDataOutputStream.write(getThumbnailBytes());
        }
        byteOrderedDataOutputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        return i10;
    }

    private static Pair<Integer, Integer> guessDataFormat(String str) {
        int i;
        int i2;
        if (str.contains(",")) {
            String[] split = str.split(",");
            Pair<Integer, Integer> guessDataFormat = guessDataFormat(split[0]);
            if (((Integer) guessDataFormat.first).intValue() == 2) {
                return guessDataFormat;
            }
            for (int i3 = 1; i3 < split.length; i3++) {
                Pair<Integer, Integer> guessDataFormat2 = guessDataFormat(split[i3]);
                if (guessDataFormat2.first == guessDataFormat.first || guessDataFormat2.second == guessDataFormat.first) {
                    i = ((Integer) guessDataFormat.first).intValue();
                } else {
                    i = -1;
                }
                if (((Integer) guessDataFormat.second).intValue() == -1 || !(guessDataFormat2.first == guessDataFormat.second || guessDataFormat2.second == guessDataFormat.second)) {
                    i2 = -1;
                } else {
                    i2 = ((Integer) guessDataFormat.second).intValue();
                }
                if (i == -1 && i2 == -1) {
                    return new Pair<>(2, -1);
                }
                if (i == -1) {
                    guessDataFormat = new Pair<>(Integer.valueOf(i2), -1);
                } else if (i2 == -1) {
                    guessDataFormat = new Pair<>(Integer.valueOf(i), -1);
                }
            }
            return guessDataFormat;
        } else if (str.contains("/")) {
            String[] split2 = str.split("/");
            if (split2.length == 2) {
                try {
                    long parseDouble = (long) Double.parseDouble(split2[0]);
                    long parseDouble2 = (long) Double.parseDouble(split2[1]);
                    if (parseDouble < 0 || parseDouble2 < 0) {
                        return new Pair<>(10, -1);
                    }
                    if (parseDouble > 2147483647L || parseDouble2 > 2147483647L) {
                        return new Pair<>(5, -1);
                    }
                    return new Pair<>(10, 5);
                } catch (NumberFormatException e) {
                }
            }
            return new Pair<>(2, -1);
        } else {
            try {
                Long valueOf = Long.valueOf(Long.parseLong(str));
                if (valueOf.longValue() >= 0 && valueOf.longValue() <= 65535) {
                    return new Pair<>(3, 4);
                }
                if (valueOf.longValue() < 0) {
                    return new Pair<>(9, -1);
                }
                return new Pair<>(4, -1);
            } catch (NumberFormatException e2) {
                try {
                    Double.parseDouble(str);
                    return new Pair<>(12, -1);
                } catch (NumberFormatException e3) {
                    return new Pair<>(2, -1);
                }
            }
        }
    }

    private void swapBasedOnImageSize(int i, int i2) {
        if (!this.mAttributes[i].isEmpty() && !this.mAttributes[i2].isEmpty()) {
            ExifAttribute exifAttribute = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_LENGTH);
            ExifAttribute exifAttribute2 = (ExifAttribute) this.mAttributes[i].get(TAG_IMAGE_WIDTH);
            ExifAttribute exifAttribute3 = (ExifAttribute) this.mAttributes[i2].get(TAG_IMAGE_LENGTH);
            ExifAttribute exifAttribute4 = (ExifAttribute) this.mAttributes[i2].get(TAG_IMAGE_WIDTH);
            if (exifAttribute != null && exifAttribute2 != null && exifAttribute3 != null && exifAttribute4 != null) {
                int intValue = exifAttribute.getIntValue(this.mExifByteOrder);
                int intValue2 = exifAttribute2.getIntValue(this.mExifByteOrder);
                int intValue3 = exifAttribute3.getIntValue(this.mExifByteOrder);
                int intValue4 = exifAttribute4.getIntValue(this.mExifByteOrder);
                if (intValue < intValue3 && intValue2 < intValue4) {
                    HashMap hashMap = this.mAttributes[i];
                    this.mAttributes[i] = this.mAttributes[i2];
                    this.mAttributes[i2] = hashMap;
                }
            }
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    private static int copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[8192];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return i;
            }
            i += read;
            outputStream.write(bArr, 0, read);
        }
    }

    private static long[] convertToLongArray(Object obj) {
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            long[] jArr = new long[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                jArr[i] = (long) iArr[i];
            }
            return jArr;
        } else if (obj instanceof long[]) {
            return (long[]) obj;
        } else {
            return null;
        }
    }
}
