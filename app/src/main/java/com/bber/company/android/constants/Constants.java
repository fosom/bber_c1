package com.bber.company.android.constants;

import com.bber.company.android.app.MyApplication;
import com.bber.company.android.tools.SharedPreferencesUtils;
import com.facebook.common.util.ByteConstants;

public class Constants {

    public static final String IMAGE_PIPELINE_CACHE_DIR = "bber";//默认图所放路径的文件夹名
    public static final int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;//默认图磁盘缓存的最大值
    public static final int MAX_DISK_CACHE_LOW_SIZE = 30 * ByteConstants.MB;//默认图低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_VERYLOW_SIZE = 10 * ByteConstants.MB;//默认图极低磁盘空间缓存的最大值
    public static final int MAX_MEMORY_CACHE_SIZE = (int) Runtime.getRuntime().maxMemory() / 4;
    /*
    *Flurry Analytics
    * */
    public static final String API_KEY = "R565T77PF7KM5245T29T";
    /*
    * IM
    * */
    public static final String XMPP_DOMU = "bber.cc";//
    public static final int XMPP_PORT = 5222;
    public static final String MSG_TYPE_TEXT = "text";//文本消息
    public static final String MSG_TYPE_IMG = "image";//图片
    public static final String MSG_TYPE_VOICE = "voice";//语音
    public static final String MSG_TYPE_ORDER = "order";//订单
    public static final String MSG_TYPE_CARD = "card";//名片
    public static final String MSG_TYPE_LOCATION = "location";//位置
    public static final String ACTION_HOME = "action_home";
    public static final String ACTION_MSG = "action_msg";
    public static final String ACTION_CUSTOMER = "action_customer";
    public static final String ACTION_HISTORY = "action_history";
    public static final String ACTION_FAVORITE = "action_favorite";
    public static final String ACTION_SETTING = "action_setting";
    public static final String ACTION_BUSSINESS = "action_setting";
    public static final String SYSTEM_TIP = "system_tip";
    public static final String FIRST_OPEN = "first_open";
    //群聊的广播
    public static final String ACTION_CHAT_IMG = "chat_image";
    public static final int UPDATE_MSG = 0;
    public static final int NOTIFY_ID = 0x90;
    /* 裁剪图片 */
    public static final int REQUEST_CODE_PHOTO_CUT = 64412;
    /* 从相册选择 */
    public static final int REQUEST_CODE_PHOTO_ALBUM = 64410;
    /* 拍照 */
    public static final int REQUEST_CODE_PHOTO_GRAPH = 64411;
    public static final int REQUEST_CODE_SEND_LOCATION = 64414;
    public static final int REQUEST_CODE_SEARCH_COUNTRY = 64415;
    public static final int REQUEST_CODE_VOUCHE_RESULT = 64416;
    public static final String USERID = "userId";
    public static final String HEADURL = "userPortrait";
    public static final String USERNAME = "userName";
    public static final String UBSEX = "ubsex";
    public static final String BIRTHDAY = "uBirthday";
    public static final String USERICON = "userIcon";
    public static final String SESSION = "session";
    public static final String PHONE = "phone";
    public static final String CHECK_PHONE = "checkPhone";
    public static final String CHAT_SELLER_ID = "chatSellerId";
    /**
     * 聊天手机开关
     */
    public static final String PHONEIMSWITCH = "BuyerPhoneIMSwitch";
    /**
     * 聊天会员开关
     */
    public static final String VIPIMSWITCH = "buyerVipIMSwitch";
    public static final String FREEVIP = "getfreeVip";
    public static final String VOUCHERFROM = "voucherFrom";
    public static final String VIP_LEVEL = "vipLevel";
    public static final String VIP_ID = "vipid";
    public static final String VIP_NAME = "vipname";
    public static final String VIP_START_TIME = "vipstarttime";
    public static final String VIP_END_TIEM = "vipendtime";
    public static final String PAY_MONEY = "payMoney";
    public static final String USER_MONEY = "userMoney";
    public static final String INVITECODE = "invitecode";
    public static final String GAMEFREEMONEY = "gameFreeMoney";
    public static final String WITHDRAWSTATUS = "withdrawStatus";
    public static final String USER_PHONE = "ubPhone";
    public static final String UNLIMITE_LOCATINO = "不限地区";
    public static final int COME_FROM_CHAT_ROOM = 1;
    public static final int COME_FROM_VIRTUAL_GOODS = 2;
    public static final int COME_FROM_HISTORY_LIST = 3;
    public static final int VOUCHER_FROM_ME = 0;
    public static final int VOUCHER_FROM_CONFIRM_ORDER = 1;
    /**
     * 支付方式
     * 人工：999999
     * 钱包：888888
     * 微信扫码：40
     * 微信：401
     * 银联无卡：51
     * 银联扫码：185
     * QQ扫码：184
     * QQ支付：1841
     * 支付宝：30
     */


    public static final int PAYDEFAULT = 999999;
    public static final int ALIPAY_CODE = 30;
    public static final int WECHATPAY_CODE = 40;
    public static final int WECHATPAY_H5_CODE = 401;
    public static final int QQ_CODE = 50;
    public static final int PED_QQ_CODE = 184;
    public static final int PED_QQ_H5_CODE = 1841;
    public static final int UNIONPAY_CODE = 51;
    public static final int UNIONPAY_SCAN_CODE = 185;
    /**
     * 用户类型	1买方，2卖方
     */
    public static final int USERTYPE = 1;

    public static final String ISRMINDING = "isReminding";

    public static Constants constants;
    public static boolean ISGETDATA = false;
    //是否重新连接
    public static boolean ISCONTACT = false;
    //是否断了链接
    public static boolean ISLink = false;
    //是否跳转了视频界面，因为关闭了视频界面的话，不需要刷新webview
    public static boolean isStartActivity = false;
    //长时间断开以后，发出socket没有回应。
    public static boolean ISLongTimeContact = false;
    /**
     * 渠道号码
     * Android的渠道号，从0开始到31，IOS的渠道号是从100开始  bber644446
     */
    public static final int APK_CHANNEL_SOURCE_ID = 0;
    public static boolean FLURRY_ENABLE = true; //发行模式的开关


    /**
     * *************************这里是UAT连接配置************************************
     */  /*
    //从IM-file-server获取IPList的接口：
    //UAT  username: admin ; password: tnw*J7nxgaidihuFFnz
    public static String getIP = "http://10.10.190.13:8383/im-file-server/user/login";
    public static String getIP_password = "tnw*J7nxgaidihuFFnz";

    //get bberUser, bberOrder, im_server
    public static String URL = SharedPreferencesUtils.get(MyApplication.getContext(), "URL",
            "http://10.10.190.13:80/bber/bberUser/") + "";
    public static String ORDER_URL = SharedPreferencesUtils.get(MyApplication.getContext(), "ORDER_URL",
            "http://10.10.190.13:80/") + "";

    //flurry开关, UAT
    public String XMPP_HOST = SharedPreferencesUtils.get(MyApplication.getContext(), "XMPP_HOST",
            "10.10.190.13") + "";
    public String IM_FILE_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "IM_FILE_SERVER",
            "http://10.10.190.13:8383/") + "";
    public String PUSH_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "PUSH_SERVER",
            "http://10.10.190.13:8011/") + "";
    public String randomURL = SharedPreferencesUtils.get(MyApplication.getContext(), "randomURL",
            "http://10.10.190.13:80/") + "";
    public String H5_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "H5_SERVER",
            "http://10.10.190.13:80/") + "";
*/
    /**
     * *************************这里是PROD-43连接配置************************************
     */
    //从IM-file-server获取IPList的接口：
    //UAT  username: admin ; password: tnwJ7nxgaidihuFFnz
    public static String getIP = "http://148.66.63.43:8383/im-file-server/user/login";
    public static String getIP_password = "tnw*J7nxgaidihuFFnz";

    //get bberUser, bberOrder, im_server
    public static String URL = SharedPreferencesUtils.get(MyApplication.getContext(), "URL",
            "http://148.66.63.43:80/bber/bberUser/") + "";
    public static String ORDER_URL = SharedPreferencesUtils.get(MyApplication.getContext(), "ORDER_URL",
            "http://148.66.63.43:80/") + "";

    //flurry开关, UAT
    public String XMPP_HOST = SharedPreferencesUtils.get(MyApplication.getContext(), "XMPP_HOST",
            "148.66.63.43") + "";
    public String IM_FILE_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "IM_FILE_SERVER",
            "http://148.66.63.43:8383/") + "";
    public String PUSH_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "PUSH_SERVER",
            "http://148.66.63.43:8011/") + "";
    public String randomURL = SharedPreferencesUtils.get(MyApplication.getContext(), "randomURL",
            "http://148.66.63.43:80/") + "";
    public String H5_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "H5_SERVER",
            "http://148.66.63.43:80/") + "";

    /**
     * *************************这里是PROD连接配置************************************
     */   /*
    //从IM-file-server获取IPList的接口：
    //PROD username: admin ; password: bber!@#
    public static String getIP = "http://iplist.papapabn5946.info/im-file-server/user/login";
    public static String getIP_password = "bber!@#";

    //get bberUser, bberOrder, im_server
    //PROD: http://lb.papapabn5946.info/bber/bberUser/; OR  http://13.94.28.27:80/bber/bberUser/;
    public static String URL = SharedPreferencesUtils.get(MyApplication.getContext(), "URL",
            "http://lb.papapabn5946.info/bber/bberUser/") + "";
    public static String ORDER_URL = SharedPreferencesUtils.get(MyApplication.getContext(), "ORDER_URL",
            "http://lb.papapabn5946.info/") + "";

    //flurry开关, PROD
    public String XMPP_HOST = SharedPreferencesUtils.get(MyApplication.getContext(), "XMPP_HOST",
            "23.97.75.96") + "";
    public String IM_FILE_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "IM_FILE_SERVER",
            "http://lb.papapabn5946.info:8383/") + "";
    public String PUSH_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "PUSH_SERVER",
            "http://lb.papapabn5946.info:8011/") + "";
    public String randomURL = SharedPreferencesUtils.get(MyApplication.getContext(), "randomURL",
            "http://lb.papapabn5946.info/") + "";
    public String H5_SERVER = SharedPreferencesUtils.get(MyApplication.getContext(), "H5_SERVER",
            "http://lb.papapabn5946.info/") + "";
*/
    /**
     ********************** 下面是接口请求部分 ****************************************
     */
    /**
     * 买家获取userpayvip
     * phone String    用户id
     */
    public static String buyerUserPayVip = ORDER_URL + "/order/business/buyerUserPayVip.do";
    public static String getUserVipZoneUrl = URL + "isLogin/vipZone/getUserVipZoneUrl.do";
    public static String setUserVipZoneUrl = URL + "isLogin/vipZone/setUserVipZoneUrl.do";
    /**
     * 获取常见问题
     */
    public static String commonProblem = URL + "commonProblem/findCommonProblemList.do";

    /**
     * 验证session（登录状态）
     * <p>
     * type  1:C端     2:B端
     */
    public String sessionIsInvalid = URL + "userLogin/sessionIsInvalid.do";

    /*
    * h5的IP地址
    * inviteCode string    邀请码
    * */
    public String getH5Ip = H5_SERVER + "/mobile/#/moments/?";
    /*
      * 上传头像
      * file File    图片文件
      * ubuyer string   用户id
      * */
    public String uploadImg = URL + "isLogin/personal/uploadPortrait.do";
    /*
      * 获取用户信息
      * id int   userid
      * */
    public String getUserInfo = URL + "isLogin/personal/buyerHome.do";
    /*
     * 修改密码
     * userId int 用户id
     * session String   登录标准
     * oldPassword string 用户旧密码
     * newPassword string 用户新密码
     * */
    public String updatePwd = URL + "isLogin/personal/updateBuyerPwd.do";

    /*
    * 获取用户等级
    * */
    public String getSellerLevel = URL + "isLogin/personal/getSellerLevel.do";
    /**
     * 匹配
     * <p>
     * buyerId  int  用户id
     * latitude   Double     纬度
     * longitude	  Double	经度
     * level int 所匹配的妹子等级
     * keyStep  int 每次消耗的钥匙数量
     */
    public String matching = URL + "isLogin/buyer/matching.do";
    /*
      * 获取某个妹子信息
      * sellerId	int	   妹子ID
      * */
    public String getSellerInfo = URL + "isLogin/sellerInfo/getSellerInfo.do";

    /*
        * 获取某个妹子ID
        * sellerId	int	   妹子ID
        * */
    public String onlineservice = URL + "po/isLogin/onlineservice.do";

    /*
         * 获取某个妹子信息
         * sellerId	int	   妹子ID
         * */
    public String getVirtualSellerId = URL + "isLogin/sellerInfo/getVirtualSellerId.do";
    /*
        * 获取订单
        *  sellerId	int	   卖家ID
        * buyerId	int	  买家ID
        * */
    public String incrConnectCount = ORDER_URL + "/order/business/incrConnectCount.do";
    /*
    * 更新订单
    *  sellerId	int	   卖家ID
    * buyerId	int	  买家ID
    * */
    public String getOrderById = ORDER_URL + "/order/business/getOrderById.do";
    public String getOrderListById = ORDER_URL + "/order/business/getOrderListById.do";
    /*
   * 更改订单状态
   *  id	int	   订单ID(服务端数据库ID)
   * status	int	  订单状态
   * key   String  Md5加密字符
   * */
    public String updateOrder = ORDER_URL + "/order/business/updateOrder.do";
    /*
   * 取消订单
   *  id	int	   订单ID(服务端数据库ID)
   * flag int	  卖家买家标示   1是B端  2是C端
   * key   String  Md5加密字符
   * */
    /*
    * 获取vip信息
    * */
    public String getVipList = ORDER_URL + "/order/vipManage/getVipList.do";



    //注册-邀请码
    public String checkBuyerInviteCode = URL + "userRegister/checkBuyerInviteCode.do";
    public String getBuyerInviteCode = URL + "userRegister/getBuyerInviteCode.do";
    public String getBuyerInviteCodes = URL + "isLogin/personal/getBuyerInviteCodes.do";
    public String getUseInviteCodeBuyerList = URL + "isLogin/personal/getUseInviteCodeBuyerList.do";

    //注册-通过邮箱获取验证码
    public String getEmailCode = URL + "userRegister/getEmailCode.do";
    public String checkEmailCode = URL + "userRegister/checkEmailCode.do";
    //登录 userEmail String    邮箱       * password String   密码
    public static  String buyerLogin = URL + "userLogin/buyerLogin.do";
    public String checkPwd = URL + "userRegister/checkPwd.do";
    //注册-提交注册信息
    public String registerBuyer = URL + "userRegister/registerBuyer.do";
    public String forgetEmailCode = URL + "userLogin/updatePwd/getEmailCode.do";
    //忘记密码--重置密码  * userEmail String    邮箱 * password String   密码
    public String findPwd = URL + "userLogin/updatePwd/findBuyerPwd.do";
    public String changePhone = URL + "bber/user/personal/changePhone.do";
    public String getNextCashCardRegular = URL + "isLogin/personal/getNextCashCardRegular.do";
    public String buyerLoginOut = URL + "isLogin/personal/buyerLoginOut.do";

    /*
 * 评价订单
 *  orderId	int	   订单ID(服务端数据库ID)
 * sellerId	int	  卖家ID
 * score  int 分数
 * comment   string 评论汉字
 * key   String  Md5加密字符
 * */
    public String changeOrderStatus = ORDER_URL + "/order/business/changeOrderStatus.do";
    /*
   * 获得卖家的评价
   * sellerId	int	  卖家ID
   * */
    public String cancleOrder = ORDER_URL + "/order/business/cancleOrder.do";
    /*
      * 获取买家订单状态
      * buyerId	int	  买家ID
      * */
    public String commentOrder = ORDER_URL + "/order/business/commentOrder.do";
    /*
   * 签到
   * type	String	 动作类型 (1表示获取签到所得的钥匙 2表示用户进行签到动作 )
   * */
    public String getAllLables = ORDER_URL + "/order/business/getAllLables.do";
    /*
     * 获取银行卡信息
     * bankId	String 	银行卡id
     *bankCard	String	银行卡号码
     *cardName	String	持卡人姓名
     **/
    public String getUnCommentOrder = URL + "isLogin/getUnCommentOrder.do";
    /*
* 获取银行卡信息
* bankId	String 	银行卡id
*bankCard	String	银行卡号码
*cardName	String	持卡人姓名
* */
    public String signIn = URL + "isLogin/personal/signIn.do";
    /*
  * 获取银行卡信息
  * bankId	String 	银行卡id
  *bankCard	String	银行卡号码
  *cardName	String	持卡人姓名
  * */
    public String getCashCardListByBuyerId = URL + "isLogin/personal/getCashCardListByBuyerId.do";
    /*
* 用户绑定银行卡
* bankId	String 	银行卡id
*bankCard	String	银行卡号码
*cardName	String	持卡人姓名
* */
    public String getBuyerBankCard = URL + "isLogin/personal/getBuyerBankCard.do";
    /*
   * 提现
   * organiId  int  机构id
   * flag  `      string  B,C
   *money	int     提现金额
   *key	String  Md5加密字符
   * */
    public String getInviteCodeSwitch = URL + "userLogin/getInviteCodeSwitch.do";
    public String updateBuyerBankCard = URL + "isLogin/personal/updateBuyerBankCard.do";

    /*
  * 检查新版本
  * latestVersion  	String 	   当前版本号
  * os                        String	    android或者ios
  * cilentType	        int	        1表示c端，2表示B端
  * */

    //人工提现
    public String manualDraw = ORDER_URL + "order/business/manualDraw.do";
    public String updateVersion = URL + "userLogin/updateVersion.do";
    /*
    * IM 发送图片
    * */
    public String sendFile = IM_FILE_SERVER + "/im-file-server/upload/file";
    /*
    * 注册-验证昵称
    *
    * userName  string     昵称（区分大小写,字母,汉字）
    *
    * */
    public String checkName = URL + "userRegister/checkNameByName.do";
    /*
       * 注册-提交注册信息
       *
       * userName String   用户昵称
       * password  String   密码
       * inviteCode  String    邀请码
       * */
    public String registerBuyerByName = URL + "userRegister/registerBuyerByName.do";
    /*
     * 登录
     * userName String    昵称
     * password String   密码
     * */
    public String buyerLoginByName = URL + "userLogin/buyerLoginByName.do";
    /*
    * 推送借口
    * id   String  im账号
    * msg_type     String     1表示不显示消息内容             2为显示消息内容
    * msg               String     消息的内容
    *key                 String        MD5加密这段(id+ msg_type + msg)
    * */
    /*
     * 输入手机号码
     * phone String    手机号
     * */
    public String buyerUpdataPhone = URL + "isLogin/personal/UpdataPhone.do";
    /**
     * 验证用户是否有绑定手机号码
     * phone String    用户id
     */
    public String buyerCheckPhone = URL + "isLogin/personal/CheckPhone.do";
    public String send = PUSH_SERVER + "/bberpush/send/send.do";
    /*
    *
    * 验证用户手机是否存在
    * phone String    用户id
    * */
    public String phoneIsRegisterNoLogin = URL + "userLogin/phoneIsRegister.do";
    /*
    *
    * 验证用户手机是否存在
    * phone String    用户id
    * */
    public String phoneIsRegister = URL + "isLogin/personal/phoneIsRegister.do";
    /*
    *
    * 修改手机密码，未登录状态
    * phone String    用户id
    * */
    public String updateBuyerPwdByPhone = URL + "userLogin/updateBuyerPwdByPhone.do";
    /*
    *
    * 修改手机密码，未登录状态
    * phone String    用户id
    * */
    public String updateBuyerUserLeaning = URL + "isLogin/personal/updateBuyerUserLeaning.do";
    /**
     * 获取SMS
     * phone String    用户id
     */
    public String getSmsCode = URL + "userRegister/getSmsCode.do";
    /**
     * 验证手机
     * phone String    用户id
     */
    public String checkSmsCode = URL + "userRegister/checkSmsCode.do";
    /**
     * 获取SMS
     * phone String    用户id
     */
    public String buyerPushMessage = URL + "isLogin/personal/buyerPushMessage.do";
    /*
    *
    * 获得个人的喜好列表
    * phone String    用户id
    * */
    public String getBuyerUserLeaning = URL + "isLogin/personal/getBuyerUserLeaning.do";
    /*
               *
               * 获取信息的推送
               * phone String    用户id
               * */
    public String getQQNumber = URL + "userLogin/getQQNumber.do";
    /*
       *
       * 获取信息的推送
       * phone String    用户id
       * */
    public String getEventURL = URL + "userLogin/getEventURL.do";
    /*
       *
       * 广告的链接
       * phone String    用户id
       * */
    public String adsList = URL + "ads/list.do";
    /*
       *
       * 广告的点击数量
       * phone String    用户id
       * */
    public String adsAddTrace = URL + "ads/addTrace.do";
    /*
       *
       * 获取信息的推送
       * phone String    用户id
       * */
    public String getDiscount = URL + "isLogin/buyer/getDiscount.do";
    /*
       *
       * 获取信息的推送
       * phone String    用户id
       * */
    public String getBuyerPhoneIMSwitch = URL + "isLogin/buyer/getBuyerPhoneIMSwitch.do";
    /*
       *
       * 获取商户的list的列表
       * phone String    用户id
       * */
    public String getShopListForBuyer = URL + "shop/getShopListForBuyer.do";
    /*
       *
       * 买家获取userpayvip
       * phone String    用户id
       * */
    public String buyerUserVipUpgrade = ORDER_URL + "/order/business/buyerUserVipUpgrade.do";
    /*
       *
       * getBillByBuyerUser
       * */
    public String getBillByBuyerUser = ORDER_URL + "/order/business/getBillByBuyerUser.do";
    /*
       *
       * 获取支付宝，微信，银联的单笔限额接口
       * */
    public String getPayMax = URL + "userLogin/getPayMax.do";
    /*
       *
       * 获取账单
       * */
    public String getBillCList = ORDER_URL + "/order/business/getBillCList.do";
    /*
       *
       * 获取结果
       * */
    public String getVipUpgradeMoney = ORDER_URL + "/order/business/getVipUpgradeMoney.do";
    /*
       *
       * 是否开通会员
       * */
    public String getBuyerUserStatus = ORDER_URL + "/order/business/getBuyerUserStatus.do";
    /*
       *
       * 是否开通会员
       * */
    public String setBuyerUserVip = ORDER_URL + "/order/business/setBuyerUserVip.do";
    /*
       *
       * 是否开通会员
       * */
    public String payCMoney = ORDER_URL + "/order/business/payCMoney.do";
    /*
       *
       * 是否开通会员
       * */
    public String buyerUserVipByBalance = ORDER_URL + "/order/business/buyerUserVipByBalance.do";
    /*
       *
       * 是否开通会员
       * */
    public String getShopCommentList = URL + "shop/getShopCommentList.do";
    /*
       *
       * 是否开通会员
       * */
    public String insertShopComment = URL + "shop/insertShopComment.do";
    /*
       *
       * 跟据C端用户ID取得收藏商户列表
       * */
    public String getShopFavoriteById = URL + "shop/getShopFavoriteById.do";
    /*
       *
       * C端删除收藏商户接口
       * */
    public String deleteShopFavorite = URL + "shop/deleteShopFavorite.do";
    /*
       *
       * C端新增收藏商户接口
       * */
    public String insertShopFavorite = URL + "shop/insertShopFavorite.do";
    /*
       *
       * 聊图点击量接口
       * */
    public String addReadTimes = URL + "talkPicture/addReadTimes.do";
    /*
       *
       * 获取聊图资源接口
       * */
    public String getTalkPicture = URL + "talkPicture/getTalkPicture.do";
    /*
          *
          * webview界面
          * */
    public String getWebView = randomURL + "/mobile/#/moments/?head=";
    /*
          *
          * 新增意见反馈接口 POST
          * */
    public String setFeedback = URL + "isLogin/feedback/setFeedback.do";
    /*
         *
         * 充值会员 POST
         * */
    public String manualPayVip = ORDER_URL + "order/business/manualPayVip.do";
    /*
       *
       *  获取VIP视频
       * */
    /*
       *
       *  C端首页跑马灯信息接口 get
       * */
    public String getMarquee = URL + "indexMarquee/getMarquee.do";
    /*
       *
       *  保存用户请求视频最快的URL
       * */
    public String applyForTalkPictureFree = URL + "talkPicture/isLogin/applyForTalkPictureFree.do";

    /*
      *
      *  非会员是否能进入游戏大厅
      * */
    public String mobilException = URL + "/feedback/mobilException.do";
    /*
     *
     *  请求视频列表
     * */
    public String getVipZone = URL + "/isLogin/vipZone/getVipZone.do";
    /*
     *
     *  请求会员专区列表
     * */
    public String getVipZoneKeyWords = URL + "/isLogin/vipZone/getVipZoneKeyWords.do";
    /*
    *
    *  请求会员专区列表
    * */
    public String setVipZoneFree = URL + "/isLogin/vipZone/setVipZoneFree.do";
    /**
     * 请求会员专区列表
     */
    public String addClick = URL + "/isLogin/vipZone/addClick.do";
    public String appstartImageUrl = URL + "/userLogin/getBackground.do";

    public synchronized static Constants getInstance() {
        if (constants == null) {
            constants = new Constants();
        }
        return constants;
    }

}