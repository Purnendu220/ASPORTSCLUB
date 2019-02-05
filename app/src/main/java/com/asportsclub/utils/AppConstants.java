package com.asportsclub.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;




import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;


public class AppConstants {

    public static int IS_ADMIN = 2;
    public static int IS_REQUESTED_ADMIN = 1;
    public static int IS_NOT_ADMIN = 0;





    public static class Chat {
        public static final String TYPE_CHAT = "chat";
        public static final String TYPE_NORMAL = "normal";
        public static final String TYPE_CHAT_GROUP = "chat_group";
        public static final String TYPE_CHAT_IMAGE = "chat_image";
        public static final String TYPE_CHAT_TEXT = "chat_text";
        public static final String TYPE_CHAT_VIDEO = "chat_video";
        public static final String TYPE_CHAT_AUDIO = "chat_audio";
        public static final String TYPE_CHAT_DOC = "chat_doc";
        public static final String TYPE_CHAT_LOCATION = "chat_location";
        public static final String TYPE_CHAT_CONTACT = "chat_contact";

        public static final String TYPE_TYPING_START = "typing_start";
        public static final String TYPE_TYPING_STOP = "typing_stop";

//        public static final int TYPE_CHAT_ID = 1;
//        public static final int TYPE_CHAT_GROUP_ID = 2;
//        public static final int TYPE_CHAT_IMAGE_ID = 3;
//        public static final int TYPE_CHAT_VIDEO_ID = 4;
//        public static final int TYPE_CHAT_AUDIO_ID = 5;
//        public static final int TYPE_CHAT_DOC_ID = 6;
//        public static final int TYPE_CHAT_LOCATION_ID = 7;
//        public static final int TYPE_CHAT_CONTACT_ID = 7;

        public static final int TYPE_TYPING_START_ID = 5;
        public static final int TYPE_TYPING_STOP_ID = 6;

        public static final int STATUS_PENDING = 1;
        public static final int STATUS_FAILED = 2;
        public static final int STATUS_SENT = 3;
        public static final int STATUS_DELIVERED = 4;
        public static final int STATUS_SEEN = 5;
    }

    public static class Pref {
        public static final String NAME = "myu_pref";
        public static final String USER_DETAIL_MODEL_OBJECT = "user_detail_model_object";
        public static final String XMPP_RESOURECE = "xmpp_resourece";
        public static final String MYU_AUTH_TOKEN = "myu_auth_token";
    }

    public static class Limits {
        public static final int PAGINATION_LIMIT_COMMENTS = 25;
        public static final int PAGINATION_LIMIT_FOLLOW = 20;
        public static final int PAGINATION_LIMIT_BOARD_FILE = 5;
        public static final int PAGINATION_LIMIT_NEWS = 20;
        public static final int PAGINATION_LIMIT_USER = 20;
        public static final int PAGINATION_CHAT_MESSAGES = 1000;
        public static final int PAGINATION_LIMIT_USER_SEARCH = 20;
        public static final int NEWS_FEED_COLLAGE_LIMIT = 5;
        public static int POST_IMAGE_UPLOAD_LIMIT = 8;
        public static final int POST_IMAGE_UPLOAD_CHAT_LIMIT = 10;
        public static final int POST_VIDEO_UPLOAD_LIMIT = 1;
        public static final int NEWS_FEED_TEXT_MAX_LINES = 5;
        public static final int TAGGING_SUGGESTION_START_LENGTH = 0;
        public static final int CHAT_MESSAGE_LOAD_MORE = 50;
        public static final int VIDEO_SIZE_LIMIT = 50;//Mb
        public static int AUDIO_RECORD_LIMIT_SEC;//sec
        public static int VIDEO_RECORD_DURATION_LIMIT;//sec
        public static int FILE_NAME_LENGHT_LIMIT = 50;//sec
        public static int VIDEO_BITRATE = 2500;//kb
        public static int BOARD_NAME_LIMIT = 35;//kb
        public static final int FB_AD_COUNT = 6;
        public static final int CHAT_INACTIVE_USER_DAYS_LIMIT = 30;//days

        public static boolean isVideoSizeValid(File file) {
            if ((file.length() / (1024 * 1024)) < VIDEO_SIZE_LIMIT) {
                return true;
            }
            return false;
        }
    }

    public static class Values {
        public static final int IMAGE_RESOLUTION_COMPRESSION = 1440;
        //                public static final int IMAGE_RESOLUTION_COMPRESSION = 1920;
        public static final int IMAGE_THUMBNAIL_RESOLUTION_COMPRESSION = 256;
        public static final int IMAGE_QUALITY = 85;
//        public static final int IMAGE_QUALITY = 95;
    }

    public static class RequestCode {
        public static final int MY_PERMISSIONS_REQUEST = 101;
        public static final int VIDEO_TRIMMER_REQUEST = 102;
        public static final int MEDIA_PICKER_REQUEST = 103;
        public static final int DOCUMENT_FILES_REQUEST = 104;
        public static final int DOCUMENT_FILES_REQUEST_EXIST_MYU = 105;
        public static final int DOCUMENT_FILES_REQUEST_EXIST_MYU_SEARCH = 106;
        public static final int SCAN_CODE_REQUEST = 107;
    }

    public static class ApiParamValue {
        public static final String OBJECT_TYPE_NEWS = "news";
        public static final String AUTHENTICATION_ERROR = "401";
        public static final String SALT = "salt";
        public static final int NEWS_TYPE_PROMOTED = 3;
        public static final String FORCE_UPDATE_ERROR = "426";
        public static final String RESPONSE_ERROR = "5XX";
        //        public static final String V2_USER_INTERNAL_LOGIN = "v2_user_internal_login";
        public static final String V2_USER_INTERNAL_LOGIN = "v2Tov3ForceUpdate";
        public static final String FORCE_UPDATE_MESSAGE = "FORCE_UPDATE";
        public static final String UPDATE_SUGGESTION = "UPDATE_SUGGESTION";
        public static final String MEDIA_TYPE_AUDIO = "Audio";
        public static final String MEDIA_TYPE_IMAGE = "Image";
        public static final String MEDIA_TYPE_VIDEO = "Video";
        public static final String MEDIA_TYPE_DOC_PDF = "PDF";
        public static final String MEDIA_TYPE_DOC_XLSX = "XLSX";
        public static final String MEDIA_TYPE_DOC_XLS = "XLS";
        public static final String MEDIA_TYPE_DOC_DOC = "DOC";
        public static final String MEDIA_TYPE_DOC_DOCX = "DOCX";
        public static final String MEDIA_TYPE_DOC_PPT = "PPT";
        public static final String MEDIA_TYPE_DOC_PPTX = "PPTX";
        public static final String MEDIA_FOR_USER_PROFILE = "UserProfile";
        public static final String MEDIA_FOR_USER_BG = "UserBackGround";
        public static final String MEDIA_FOR_NEWS_POST = "NewsPost";
        public static final String MEDIA_FOR_CHAT = "chat";
        public static final String MEDIA_FOR_USER = "user";
        public static final String SUCCESS_RESPONSE_CODE = "2XX";
        public static final String TYPE_BOARD_POST = "boardPost";
        public static final String TYPE_OWN_POST = "ownPost";
        public static final String TYPE_HASHTAG = "hashTag";
        public static final String TYPE_NEWS_FEED = "newsFeed";
        public static final String TYPE_FOLLOWING_CATAGORY_FEED = "following";
        public static final String TYPE_YOU_CATAGORY_FEED = "you";
        public static final String USER = "user";
        public static final String NEXT = "next";
        public static final String PREVIOUS = "previous";
        public static final String GENDER_MALE = "Male";
        public static final String GENDER_FEMALE = "Female";
        public static final String GENDER_COED = "CO_ED";
        public static final String ACTION_TARGET_WEBVIEW = "1";
        public static final String ACTION_TARGET_EXTERNAL_BROWSER = "2";

        /**
         * for mediaName value
         ***/
        public static String getFileName(File file) {
            return file.getName();
        }
    }

    public static class XMPP {
        public static final String SERVICE_NAME = "localhost";
        //        public static final String SERVICE_NAME = "slave";
        public static final String HOST_NAME_LIVE = "chat.myu.co";
        public static final String HOST_NAME_BETA = "beta-chat.myu.co";
        public static final String HOST_NAME_ALPHA = "qa-chat.myu.co";
//        public static final String HOST_NAME_ALPHA = "qa.profive.co";
    }

    public static class DataKey {
        public static final String DOCS_URL_STRING = "docs_url_string";
        public static final String DOC_MEDIA_TYPE = "doc_media_type";
        public static final String DOCS_URL_STRING_ID = "docs_url_string_id";
        public static final String DOCS_URL_FILE_NAME = "docs_url_file_name";
        public static final String MEDIA_MODEL_OBJECT = "media_model_object";
        public static final String POST_DATA_FILE_OBJECT = "post_data_file_object";
        public static final String POST_DATA_OBJECT = "post_data_object";
        public static final String POST_DATA_CANCEL_BOOLEAN = "post_data_cancel_boolean";
        public static final String POST_DATA_RETRY_BOOLEAN = "post_data_retry_boolean";
        public static final String POST_DATA_PAUSE_BOOLEAN = "post_data_pause_boolean";
        public static final String SELECT_HOME_PAGE_INDEX_INT = "select_home_page_index_int";
        public static final String POST_SERVICE_STOP_BOOLEAN = "post_service_stop_boolean";
        public static final String OPEN_COMMENTS_BOOLEAN = "OPEN_COMMENTS_BOOLEAN";
        public static final String NEWS_MODEL_OBJECT = "news_model_object";
        public static final String PAGER_POSITION_INT = "pager_position_int";
        public static final String USER_DETAIL_MODEL_OBJECT = "user_detail_model_object";
        public static final String NEWS_LIST_TYPE_ENUM = "news_list_type";
        public static final String USER_MODEL_OBJECT = "user_model_object";
        public static final String VIDEO_TRIMMER_OPTIONS_OBJECT = "video_trimmer_options_object";
        public static final String VIDEO_TRIMMER_RESULT_URI_STRING = "video_trimmer_result_uri_string";
        public static final String VIDEO_TRIMMER_RESULT_PATH_STRING = "video_trimmer_result_path_string";
        public static final String BOARD_MODEL_OBJECT = "board_model_object";
        public static final String HASH_TAG_STRING = "hash_tag_string";
        public static final String SHOW_LIKE_LIST_BOOLEAN = "show_like_list_boolean";
        public static final String SHOW_VIEW_LIST_BOOLEAN = "show_view_list_boolean";
        public static final String IS_MY_PROFILE_BOOLEAN = "is_my_profile_boolean";
        public static final String NEWS_TYPE_STRING = "news_type_string";
        public static final String IMAGE_URL_STRING = "image_url_string";
        public static final String XMPP_USERNAME_STRING = "xmpp_username_string";
        public static final String XMPP_PASSWORD_STRING = "xmpp_password_string";
        public static final String Chat_MESSAGE_MODEL_OBJECT = "chat_message_model";
        public static final String IS_NAVIGATION_FROM_SHARE_CLASS = "is_navigation_from_share_class";
        public static final String IS_NAVIGATION_FROM_SHARE = "is_navigation_from_share";
        public static final String CLASS_CODE = "class_code";
        public static final String SELECT_EXPLORE_PAGE_INDEX_INT = "select_explore_page";
        public static final String ATTENDANCEITEM_MODEL_OBJECT = "attendanceitem_model_object";
        public static final String ATTENDANCE_CODE = "attendancecode";
        public static final String ATTENDANCE_PRESENT = "attendancepresent";
        public static final String ATTENDANCE_ABSENT= "attendanceabsent";
        public static final String BoardATTEANDANCE_CONFIGID= "boardAttendanceConfigId";
    }

    public static class ApiParamKey {
        public static final String OBJECT_TYPE_ID = "objectTypeId";
        public static final String OBJECT_TYPE = "objectType";
        public static final String OBJECT_DATA_ID = "objectDataId";
        public static final String MEDIA = "media";
        public static final String MEDIA_FOR = "mediaFor";
        public static final String UNIQUE_CHAR = "uniqueChar";
        public static final String MEDIA_TYPE = "mediaType";
        public static final String MEDIA_NAME = "mediaName";
        public static final String IMAGE_CAPTION = "imageCaption";
        public static final String IMAGE = "Image";
        public static final String UPLOAD_FROM = "uploadFrom";
        public static final String USER_ID = "userId";
        public static final String NEWS_OWNERID = "newsOwnerId";
        public static final String KEY_1 = "key1";
        public static final String KEY_2 = "key2";
        public static final String KEY_3 = "key3";
        public static final String USERNAME = "username";
        public static final String usernameOrEmail = "usernameOrEmail";
        public static final String BARE_PEER = "bare_peer";
        public static final String VIRTUAL_NEWS_ID = "virtualNewsId";
        public static final String NEWS_COMMENT_TEXT = "newsCommentText";
        public static final String IS_ARCHIVED = "isArchived";
        public static final String NEWS_TEXT = "newsText";
        public static final String IS_WALL_POST = "isWallPost";
        public static final String BOARD_IDS = "boardIds";
        public static final String TYPE = "type";
        public static final String EVENT = "event";
        public static final String HASH_TEXT = "hashText";
        public static final String CATAGORY = "notificationCategory";
        public static final String DELETE_CATAGORY = "category";

        public static final String PAGE = "page";
        public static final String STATUS = "status";
        public static final String ID_FOR_PAGE = "idForPage";
        public static final String TIMESTAMP_FOR_PAGE = "timestampForPage";
        public static final String PAGE_DIRECTION = "pageDirection";
        public static final String FILTERTYPE = "filterType";
        public static final String DATAFROMWIDER = "dataFromWiderWindow";
        public static final String EXPLOREPOPULAR = "papularCategory";
        public static final String EXPLOREEXCLUDE = "excludeUsers";
        public static final String SIZE = "size";
        public static final String ROWS = "rows";
        public static final String QUERY = "query";
        public static final String ID = "id";
        public static final String NEWS_ID = "newsId";
        public static final String LIKE_TOGGLE = "likeToggle";
        public static final String MYU_AUTH_TOKEN = "myu-auth-token";
        public static final String APP_LANGUAGE = "Content-Language";
        public static final String RESOURCE = "resource";
        public static final String APP_VERSION = "appVersion";
        public static final String FOLLOWER_USER_ID = "followerUserId";
        public static final String FOLLOWED_USER_ID = "followedUserId";
        public static final String FOLLOW_TOGGLE = "followToggle";
        public static final String BOARD_ID = "boardId";
        public static final String SEE_MORE = "seeMore";
        public static final String JOINED_BY = "joinedBy";
        public static final String NAME = "name";
        public static final String CREATEDFROM = "createdFrom";
        public static final String ABBRIVATION = "abbreviation";
        public static final String CITYID = "cityId";
        public static final String WEBSITE = "website";
        public static final String PHONE = "phone";
        public static final String PINCODE = "pincode";
        public static final String ADDRESS = "address";
        public static final String COUNTRY = "country";
        public static final String STATE = "state";
        public static final String CITY = "city";
        public static final String ISPRIME = "isprime";
        public static final String POSTCHECKFROMBOARD = "postcheck";
        public static final String ISAUDIOSELECTED = "isaudio";
        public static String TOTAL_ALLOCATED_SPACE = "totalAllocatedSpace";
        public static String CONSUMED_SPACE = "consumedSpace";
        public static String CRASHLYTICS_ERROR_TAG_SERVER_API = "server_api";
        public static String DEBUG = "debug";
        public static final String CODE = "code";
        public static final String ACTIONFOR = "actionFor";
        public static final String BOARD_ATTENDEE_CONFIGID = "boardAttendanceConfigId";
        public static final String MEMBER_TYPE = "memberType";
    }

    public static final class Twitter {
        public static final String CONSUMER_KEY = "3SmTphMaKNablHMfnMj3mQ";
        public static final String CONSUMER_SECRET = "SJ9pxd5LV0offDxGgbscWZWe7EihPbvj6SVLaXUNE8";
        public static final String log_tag = "TwitterHelper";
        public static final String PREFERENCE_NAME = "twitter_oauth";
        public static final String PREF_KEY_SECRET = "oauth_token_secret";
        public static final String PREF_KEY_TOKEN = "oauth_token";
    }

    public static final class Url {

        public static String BASE_SERVICE_LIVE = "https://api.myu.co/";
        public static String BASE_SERVICE_BETA = "https://beta-api.myu.co/";
        public static String BASE_SERVICE_ALPHA = "https://qa-api.myu.co/";

        public static final String ALL_BOARD_FILES = "/midl/api/v3/existing-media-inboards";
        public static final String BOARD_FILES = "/midl/api/v3/board/media";
        public static final String BOARDS = "/midl/api/v3/board";
        public static final String SALT = "/userservice/api/v3/user/login-handshake";
        public static final String CREATE_POST = "/midl/api/v3/news";
        public static final String GET_NEWS = "/midl/api/v3/news";
        public static final String GET_NEWS_HASHTAG = "/midl/api/v3/news/hashtag";
        public static final String GET_NOTIFICATION = "/midl/api/v3/notification";
        public static final String NEWS_LIKE = "/midl/api/v3/news/like";
        public static final String PUBLISH_NEWS = "/midl/api/v3/news/{" + ApiParamKey.ID + "}/publish";
        public static final String COMMENTS = "/midl/api/v3/news/comment";
        public static final String DELETE_NOTIFICATION = "/midl/api/v3/notification";
        public static final String MEDIA_UPLOAD = "/mediaservice/api/v3/media";
        public static final String SEARCH_BOARD_MEDIA_FILES = "/midl/api/v3/search-media-inboards";
        public static final String MEDIA_UPLOAD_EXIST = "/mediaservice/api/v3/media/existing";
        public static final String MEDIA_UPLOAD_CHAT = "/mediaservice/api/v3/media/directs3";
        public static final String SEARCH_USER = "/userservice/api/v3/user/typeahed";
        public static final String SEARCH_USER_NEW = "/userservice/api/v3/user/typeahed";
        public static final String DELETE_USER = "/midl/api/v3/notification";
        public static final String USER = "/userservice/api/v3/user";
        public static final String INITUSER = "/userservice/api/v3/user/init-data";
        public static final String USER_LIST_BASIC_INFO = " userservice/api/v3/user/basicinfo";
        public static final String FOLLOW = "/userservice/api/v3/user/follow";
        public static final String BOARD_FOLLOWER = "/midl/api/v3/board/member";
        public static final String NEWS_VIEW = "/midl/api/v3/news/view";
        public static final String JOIN_BOARDS = "/midl/api/v3/board/join";
        public static final String GET_SINGLE_NEWS = "/midl/api/v3/news";
        public static final String JOINED_BOARDS = "/midl/api/v3/board/joined";
        public static final String REPORT = "/midl/api/v3/news/poke";
        public static final String VERIFY_EMAIL = "/userservice/api/v3/user/verfication-link";
        public static final String ACCEPTREJECT = "/midl/api/v3/board/request-action";
        public static final String HASHTAG = "/midl/api/v3/typeahed";
        public static final String MENTION = "userservice/api/v3/user/myRelative";
        public static final String GET_EXPLORE_DETAIL = "/midl/api/v3/explore";
        public static final String GET_EXPLORE_DETAIL_OTHER = "/userservice/api/v3/user/explore";
        public static final String GET_EXPLORE_DETAIL_NEWS = "/midl/api/v3/news/explore";
        public static final String CHAT = "/midl/api/v3/chat";
        public static final String UPDATE_INFO_USER = "/userservice/api/v3/user/updateinfo";
        public static final String SHARE_LINK_URL = "http://html.myuapp.co/fb_share.php?newsid=";
        public static final String GET_COUNTRY = "/midl/api/v3/typeahed";
        public static final String SUBMIT_UNIVERSITY = "/midl/api/v3/master/university";
        public static final String BLOCKUSERLIST = "/userservice/api/v3/user/blocked-users";
        public static final String BLOCK_USER = "/userservice/api/v3/user/block";
        public static final String GET_FILE_TYPES = "/midl/api/v3/board/findMediaType";
        public static final String GET_PAYMENTURL = "/userservice/api/v3/user/package/payment-initiate/";
        public static final String CHANGEUNIVERSITY = "/userservice/api/v3/user/change-university-request";
        public static final String FILEDELETE = "/mediaservice/api/v3/media";
        public static final String NEWS_EDIT = "/midl/api/v3/news";
        public static final String NEWS_EDIT_VIRTUAL_COUNT = "midl/api/v3/news/virtualNewsCount";
        public static final String UPDATE_PRIME_PACKAGE = "/userservice/api/v3/user/package";
        public static final String LOG_ACTIVITY = "userservice/api/v3/user/log-activity";
        public static final String MAKE_ADMIN = "/midl/api/v3/board/admin/invitation";
        public static final String REMOVE_ADMIN = "/midl/api/v3/board/admin/remove";
        public static final String CANCEL_ADMIN = "/midl/api/v3/board/admin/cancel-invitation";
        public static final String ADD_INVITATION = "/midl/api/v3/board/add-members";
        public static final String CLASS_SEARCH = "/midl/api/v3/board/typeahead?";
        public static final String IMPRESSION = "/midl/api/v3/news/impression";
        public static final String CHAT_HISTORY = "/userservice/api/v3/chat/history";
        public static final String ATTENDANCE_HISTORY="/midl/api/v3/board/attendance";
        public static final String ATTENDANCE_HISTORY_STUDENT="/midl/api/v3/board/attendance/history";
        public static final String TAKE_ATTENDANCE_INITIATE="/midl/api/v3/board/attendance/initiate";
        public static final String GET_ATTENDEE_MEMBER="/midl/api/v3/board/attendee-member";
        public static final String GET_ATTENDANCE_CODE="/midl/api/v3/board/attendance/code";
        public static final String SEND_ATTENDANCE_MAIL="/midl/api/v3/board/attendance/send-report";
        public static final String VALIDATE_QRCODE="/midl/api/v3/board/attendance/code/validate";
    }

    public static final class Facebook {
        public static final String AD_ID_EXPLORE = "200159273966032_213342462647713";
        public static final String AD_ID_NEWS_FEEDS = "200159273966032_200185837296709";
    }


    // Google Project ID
    public static final String GOOGLE_PROJ_ID = "910462476326";
    public static String registeredDeviceTokenforGCM = "";
    public static String Myu_App_Authtoken = "";

    public static String APP_FOLDER = Environment.getExternalStorageDirectory() + File.separator + "myU" + File.separator;
    public static String APP_FOLDER_AUDIO = APP_FOLDER + "Audio" + File.separator;
    public static String APP_FOLDER_VIDEO = APP_FOLDER + "Video" + File.separator;
    public static String APP_FOLDER_IMAGE = APP_FOLDER + "Images" + File.separator;
    public static String APP_FOLDER_DOC = APP_FOLDER + "Doc" + File.separator;
    public static String APP_FOLDER_TEMP = APP_FOLDER + "Temp" + File.separator;
    public static String APP_FOLDER_DOC_ROOT = "myU" + File.separator + "Doc" + File.separator;
    public static String FILE_NAME_TRIMMED_VIDEO = "trimmed.mp4";
    public static String FILE_NAME_COMPRESSED_VIDEO = "trimmed_compressed.mp4";

    public static final String MYU_APP_KEY_AUTH_TOKEN = "Auth_Token";
    public static final String PREF_USERINFO = "userinfo";

    public static final String USER_ID = "user_id";
    public static final String USER_DEVICE_TOKEN = "user_device_token";
    public static final String USER_NAME = "user_name";
    public static final String USER_USERNAME = "user_username";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_UNIVERSITY = "user_university";
    public static final String USER_UNIVERSITY_ID = "user_university_id";
    public static final String USER_DEPARTMENT_ID = "user_department_id";
    public static final String USER_DEPARTMENT = "user_department";
    public static final String USER_TYPE = "user_type";
    public static final String USER_BIO = "user_bio";
    public static final String USER_GENDER = "user_gender";
    public static final String USER_DOB = "user_dob";
    public static final String USER_MAJOR = "user_major";
    public static final String USER_GRADUATION_YEAR = "user_graduation_user";
    public static final String USER_MOBILE = "mobileNo";
    public static final String USER_CANADD_NEWS = "canAddNews";
    public static final String USER_RECEIVE_PRIVATE_MESSEGE_NOTIFICATION = "receivePrivateMsgNotification";
    public static final String USER_RECEIVE_PRIVATE_MESSEGE = "receivePrivateMsg";
    public static final String USER_RECEIVE_COMMENT_NOTIFICATION = "receiveCommentNotification";
    public static final String USER_RECEIVE_LIKE_NOTIFICATION = "receiveLikeNotification";
    public static final String USER_RECEIVE_FOLLOWING_NOTIFICATION = "receiveFavoriteFollowNotification";
    public static final String USER_RECEIVE_NEWS_NOTIFICATION = "receiveNewPostNotification";
    public static final String USER_ALLOW_IN_POPULAR_LIST = "allowInPopularList";

    public static final String IMAGE_STORE = "Image Capture";
    public static final String USERISLOGIN = "user_login";
    public static final String DATE_FORMATE = "dd-MM-yyyy";
    public static final String DATE_FORMATE_PROMOTED = "yyyy-MM-dd";

    public static final String FEEDBACK_MAIL_ID = "info@myu.co";
    public static final String SUPPORT_MAIL_ID = "support@myu.co";


    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    private static final Pattern USERNAME_PATTERN = Pattern
            .compile("^[a-zA-Z0-9_.]+$");


    public static final int SEARCH_RESULT_LIMIT = 20;
    public static final int DELETED_BOARD = 1;
    public static final int UNARCHIVED_BOARD = 2;

    public static final int STUDENT_TYPE = 2;
    public static final int PROFESSOR_TYPE = 1;
    public static final int DEPARTMENT_TYPE = 3;
    public static final int PROMOTER_TYPE = 4;
    public static final int UNKNOWN_TYPE = -1;
    public static final int PARENT_TYPE = 5;

    public static final int BOARD_REQUEST_PENDING = 1;
    public static final int BOARD_REQUEST_JOINED = 2;
    public static final int BOARD_REQUEST_NOT_JOINED = 0;

    public static String ROLE_ADMIN = "admin";
    public static String ROLE_OWNER = "owner";

    public static UserType userType = UserType.STUDENT;




    public static String getDownloadDocFileName(String id, String fileName) {
        if (id.isEmpty()) {
//            return url.substring(url.lastIndexOf("/") + 1, url.length());
            return fileName;
        } else
//            return id + "_" + url.substring(url.lastIndexOf("/") + 1, url.length());
            return id + "_" + fileName;
    }

    public enum UserType {
        STUDENT(STUDENT_TYPE, "student"),
        PROFESSOR(PROFESSOR_TYPE, "teacher"),
        DEPARTMENT(DEPARTMENT_TYPE, "department"),
        PROMOTER(PROMOTER_TYPE, "promoter"),
        PARENT(PARENT_TYPE, "parent"),
        UNKNOWN(UNKNOWN_TYPE, "unknown");

        private final int type;
        private final String name;

        private UserType(int typee, String name) {
            this.type = typee;
            this.name = name;
        }

        public int GetType() {
            return type;
        }



        public String getLocalName(Context context) {
            String name = "";
            switch (type) {
                case STUDENT_TYPE:
                    name = "Student";
                    break;
                case PROFESSOR_TYPE:
                    name = "Teacher/Professor";
                    break;
                case DEPARTMENT_TYPE:
                    name = "Department/Club";
                    break;
                case PARENT_TYPE:
                    name = "Parent";
                    break;
                case PROMOTER_TYPE:
                    name = "";
                    break;
                case UNKNOWN_TYPE:
                    name = "";
                    break;
            }
            return name;
        }
    }



    public static UserType getUserType(int usertype) {
        UserType userType = UserType.UNKNOWN;
        switch (usertype) {
            case STUDENT_TYPE:
                userType = UserType.STUDENT;
                break;
            case PROFESSOR_TYPE:
                userType = UserType.PROFESSOR;
                break;
            case DEPARTMENT_TYPE:
                userType = UserType.DEPARTMENT;
                break;
            case PARENT_TYPE:
                userType = UserType.PARENT;
                break;
            case PROMOTER_TYPE:
                userType = UserType.PROMOTER;
                break;
        }
        return userType;
    }

    public static boolean isEmailValid(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean isUsernameValid(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }


    public static String getDate(long milliSeconds, String REQUIRED_DATETIME_FORMAT) {
        // Create a DateFormatter object for displaying date in specified format.
        String date;
        String time;
        SimpleDateFormat formatter = new SimpleDateFormat(REQUIRED_DATETIME_FORMAT);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        return formatter.format(calendar.getTime());
    }

    public static String getDateAttendance(long createddate){
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(createddate);
        SimpleDateFormat chatHeaderFormatter = new SimpleDateFormat("MMM d, yyyy - HH:mm", Locale.getDefault());
        String attendanceDate = chatHeaderFormatter.format(time.getTime());
//        int hour = time.get(Calendar.HOUR);
//        int min = time.get(Calendar.MINUTE);
        return  attendanceDate;
    }

    public static String getDateOnlyFromMilli(long createddate){
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(createddate);
        SimpleDateFormat chatHeaderFormatter = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
        String attendanceDate = chatHeaderFormatter.format(time.getTime());
//        int hour = time.get(Calendar.HOUR);
//        int min = time.get(Calendar.MINUTE);
        return  attendanceDate;
    }





    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
