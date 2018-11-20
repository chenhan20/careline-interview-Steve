let modifyEmail;//編輯時需要的對應email
let loginUser; //登入使用者資料
$(function(){
    $('.init').hide();
    getSession();//抓取session資訊
    $('.list-group-item').click(function(){
        $(".list-group-item").removeClass("active");
        $(this).addClass("active");
        call(this.id);
    });
    $("#btn_add").click(function() {
        register();
    });

    $('#btn_login').click(function(){
        login();
    });
    $('#btn_modify').click(function(){
        modify();
    });

    $(document).on('click','.modifyTable',function(){
        modifyEmail = $(this).parent().attr('id');
        $('#modify').click();
    });
    call('login'); //預設進入login
});

let modify =() =>{
    ajax({
        url : "/mission11/modify",
        method : "POST", 
        data : new FormData($("#modifyDataForm")[0]), 
        processData: false,
        contentType: false, 
        success : function(data) {
            let Msg;
            if(!data.login){
                call('login'); //預設進入login
            }else if(data.msg!=null){
                Msg=data.msg;
            }else{
                if ($.trim(data.IMGURL) === "") {
                    $(".userImg").empty().text("尚未上傳照片");
                } else {
                    $(".userImg").empty().append("<img src='"+data.IMGURL+"' style='max-width:200px; max-height:200px;' />");
                }
                Msg='更新成功';
            }
            $('.modifyMsg').text(Msg);
        }
    });
}

// RESTful方法不知道如何像是jsp一樣用tag取session取值 所以每次重刷頁面都要先來抓session資訊
let getSession = () =>{
    ajax({
        url : "/mission11/getSession",
        method : "POST", 
        success : function(data) {
            let Msg;
            if(data.msg!=null){
                Msg=data.msg;
            }else{
                loginUser = data.userData;
                if(loginUser!=null){
                    $('#userName').text(loginUser.NAME);
                }
            }
        }
    })
    
}

let register = () =>{
    ajax({
        url : "/mission3/register",
        method : "POST", 
        data : new FormData($("#registerDataForm")[0]), 
        processData: false,
        contentType: false, 
        success : function(data) {
            let Msg;
            if(data.msg!=null){
                Msg=data.msg;
            }else{
                Msg='新增成功 ID:' + data.memberId;
                $("#dataForm :input").val('');
            }
            $('.Msg').text(Msg);
        }
    });
};
let login = () =>{
    loginUser =null; //避免殘留
    ajax({
        url : "/mission5/login",
        method : "POST", 
        data : new FormData($("#loginForm")[0]), 
        processData: false,
        contentType: false, 
        success : function(data) {
            let Msg;
            if(data.errorMsg!=null){
                Msg=data.errorMsg;
                getSession();
            }else{
                loginUser = data.userMap;
                $('#userName').text(loginUser.NAME);
                Msg=data.successMsg;
                $("#loginForm :input").val('');
            }
            $('.loginMsg').text(Msg);
        }
    });
};

/**
 * 基本上用這邊控制點選流程  若有超出權限的功能 會直接導回登入頁(目前只有卡登入)
 * @param {*} method 
 */
let call = (method) =>{
    $('.init').hide();
    $('.'+method).fadeIn(600);
    switch (method){
        case 'login' :
            break;
        case 'query' :
            queryUser();
            break;
        case 'add' :
            addUser();
            break;
        case 'modify' :
            modifyUser(modifyEmail);
            break;
        default :
            $('#login').click();
    }
};

let modifyUser = (email) =>{
    let member;
    if(email!=null){
        ajax({
            url : "/mission11/getMembers",
            data : {"email" : email},
            async : false,
            success : function(data) {
                member = data.member;
            }
        });
    }else{
        $('#query').click();
    }
    renderModifyData(member);
};

let addUser = () =>{
    //前端先這樣基礎卡控  後端寫在攔截器 若沒登入也是直接擋掉
    if(loginUser!=null){
        let tbody = $("#adddataTable tbody").empty();
        let tr = $(
                "<tr>"
                + "  <td><input type='text' name='email' class='form-control' placeholder='Enter email'></td>"
                + "  <td><input type='text' name='name' class='form-control' placeholder='Enter Name'></td>"
                + "  <td><input type='password' name='password' class='form-control' placeholder='Enter Password'></td>"
                + "</tr>"
            );
        $(tbody).append(tr);
    }else{
        $('#login').click();
    }

    
};
let queryUser = () =>{
    if(loginUser!=null){
        ajax({
            url : "/mission4/getAllMembers",
            success : function(data) {
                renderQueryTable(data.memberList);
            }
        });
    }else{
        $('#login').click();
    }
}
let renderQueryTable = (memberList) => {
    var tbody = $("#querydataTable tbody").empty();
    
    if (memberList.length === 0) {
        tbody.append("<tr><td colspan='3'>N/A</td></tr>");
    } else {
        $.each(memberList, function (i, member) {
            var tr = $(
                "<tr id='"+member.EMAIL+"'>"
                + "  <td>"+member.EMAIL+"</td>"
                + "  <td>"+member.NAME+"</td>"
                + "  <td class='modifyTable cursor'><i class='fas fa-wrench'></i></td>"
                + "</tr>"
            );
            
            $(tbody).append(tr);
        });
    }
}
let renderModifyData = (member) =>{
    $('#modifyDataForm :input').val('');
    modifyEmail=null;
    $('#modifyId').val(member.ID);
    $('.modifyEmail').val(member.EMAIL);
    $('#modifyName').val(member.NAME);
    if ($.trim(member.IMGURL) === "") {
        $(".userImg").empty().text("尚未上傳照片");
    } else {
        $(".userImg").empty().append("<img src='"+member.IMGURL+"' style='max-width:200px; max-height:200px;' />");
    }
}
