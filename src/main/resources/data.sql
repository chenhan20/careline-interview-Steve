INSERT INTO `tmp` (`key`, `value`, `desc`) VALUES ('System.Start', NOW(), 'System start time.');
INSERT INTO `h_user` (`id`,`email`, `name`, `password`) VALUES ('9999999999', 'a0911558945@gmail.com', '莊承翰(管理者)','pL5k788NAn5Yl9Tlg7a6Gg==');
INSERT INTO `INTERESTS` (`INTERESTS_CODE`,`INTERESTS_VALUE`) VALUES ('Movie', '電影');
INSERT INTO `INTERESTS` (`INTERESTS_CODE`,`INTERESTS_VALUE`) VALUES ('Food', '美食');
INSERT INTO `INTERESTS` (`INTERESTS_CODE`,`INTERESTS_VALUE`) VALUES ('Sport', '運動');
INSERT INTO `INTERESTS` (`INTERESTS_CODE`,`INTERESTS_VALUE`) VALUES ('Travel', '旅遊');
INSERT INTO `INTERESTS` (`INTERESTS_CODE`,`INTERESTS_VALUE`) VALUES ('Music', '音樂');
INSERT INTO `USER_INTERESTS` (`ID`,`USER_ID`,`INTERESTS_CODE`) VALUES ('123123','9999999999', 'Music');

-- API功能
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M01_1','hello','/mission1/hello', 'hello','Y','');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M02_1','compute','/mission2/compute', 'compute','Y','四則運算');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M03_1','register','/mission3/register', 'register','Y','註冊');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M04_1','getAllMembers','/mission4/getAllMembers', 'getAllMembers','Y','查詢會員');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M05_1','login','/mission5/login', 'login','Y','登入');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M05_2','logout','/mission5/logout', 'logout','Y','登出');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M06_1','updateProfile','/mission6/updateProfile', 'updateProfile','Y','修改名稱');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M06_2','updatePassword','/mission6/updatePassword', 'updatePassword','Y','修改密碼');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M07_1','uploadPicture','/mission6/uploadPicture', 'uploadPicture','Y','上傳圖片');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M07_2','getPicture','/mission7/getPicture', 'getPicture','Y','取得圖片');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M08_1','saveInterest','/mission8/saveInterest', 'saveInterest','Y','儲存興趣');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M08_2','getInterest','/mission8/getInterest', 'getInterest','Y','取得興趣');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M11_1','call','/mission11/getMembers', 'getMembers','Y','根據信箱查詢完整會員資訊');
INSERT INTO `API_FUNCTION` (`API_CODE`,`API_NAME`,`API_URL`,`API_CLASS`,`USE_OPEN`,`API_DEPT`) VALUES ('M11_2','call','/mission11/getSession', 'getSession','Y','取得session會員資料');

