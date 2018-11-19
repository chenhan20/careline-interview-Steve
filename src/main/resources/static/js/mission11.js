$(function(){
    $('.init').hide();
    $('.query').show();
    $('.list-group-item').click(function(){
        $(".list-group-item").removeClass("active");
        $(this).addClass("active");
        call(this.id);
    });
    $("#btn_add").click(function() {
        register();
    });

    $(document).on('click','.modify',function(){
        let email = $(this).parent().attr('id');
        modifyUser(email);
        $('#modify').click();
    });

    call('query'); //預設進入query
});

let register = () =>{
    ajax({
        url : "/mission3/register",
        method : "POST", 
        data : new FormData($("#dataForm")[0]), 
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

let call = (method) =>{
    $('.init').hide();
    $('.'+method).show();
    switch (method){
        case 'query' :
            queryUser();
            break;
        case 'add' :
            addUser();
            break;
        case 'add' :
            addUser();
            break;
    }
};

let modifyUser = (email) =>{
    ajax({
        url : "/mission11/getMembers",
        data : {"email" : email},
        success : function(data) {
            renderModifyTable(data.memberList);
        }
    });
};

let addUser = () =>{
    var tbody = $("#adddataTable tbody").empty();

    var tr = $(
            "<tr>"
            + "  <td><input type='text' name='email' class='form-control' placeholder='Enter email'></td>"
            + "  <td><input type='text' name='name' class='form-control' placeholder='Enter Name'></td>"
            + "  <td><input type='password' name='password' class='form-control' placeholder='Enter Password'></td>"
            + "</tr>"
        );
    
    $(tbody).append(tr);
};
let queryUser = () =>{
    ajax({
        url : "/mission4/getAllMembers",
        success : function(data) {
            renderQueryTable(data.memberList);
        }
    });
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
                + "  <td class='modify cursor'><i class='fas fa-wrench'></i></td>"
                + "</tr>"
            );
            
            $(tbody).append(tr);
        });
    }
}
let renderModifyTable = (memberList) =>{
    var tbody = $("#querydataTable tbody").empty();
    
    $.each(memberList, function (i, member) {
        var tr = $(
            "<tr>"
            + "  <td>"+member.EMAIL+"</td>"
            + "  <td>"+member.NAME+"</td>"
            + "</tr>");
        
        $(tbody).append(tr);
    });
}
