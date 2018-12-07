
function validate() {
	var name = document.getElementById("name").value;
	if (name == '') {
		alert('Please enter a valid name.');
		return false;
	} else {
		return true;
	}
}
/*
Trigger this function for loading comments in the user-comments <div>
 */
function loadComments(id) {
	$.get("/comments/find?blogId="+id)
	.done(function(data) {
		console.log(data);
        //data.sort(compareCreatedTime);
        console.log(data);
		var innerHTML ='';
		if(data !=null){
			data.forEach(function(comment){
				innerHTML +='<div class="media mb-4"><div class="media-body"><h5 class="mt-0">'+comment.userName +'</h5>'+comment.data+'</div></div>';
			});
			$('#user-comments').html(innerHTML);
		}
		
	});
}

function compareCreatedTime(x,y){
    q = x['createdTime'];
    w = y['createdTime'];
    return (w-q);
}
/*
This function call triggers the API /postBlog with the necessary params.
 */
function postBlog() {
    var title = $('#blog_title').val();
    if (title == null || title == '' || typeof(title) != "string") {
        alert("Please enter a valid title.");
        return;
    }
    title = title.trim();

    var desc = $('#description').val();
    if (desc == null || desc == '' || typeof(desc) != "string") {
        alert("Please enter a valid description.");
        return;
    }
    desc = desc.trim();

	var data = {};
    data['title'] = title;
	data['description'] = desc;
	
	console.log(data);
	$.post("/postBlog", data)
	.done(function(data) {
		if(data.status='success') {
			alert( 'Your Blog was added!');
            window.location = "/index";
		}
		else {
			alert( data.errorMsg);
            window.location = "/login";
		}
	});
	return false;
}
/*
This function triggers the /comments/save API for creating a new comment.
 */
function postComments() {

    var comment = $('#comments').val();
    if (comment == null || comment == '' || typeof(comment) != "string") {
        alert("Please enter a valid comment.");
        return;
    }
    comment = comment.trim();
    var data = {};
    data['comments'] = comment;
    data['blogId'] = $('#blogId').val();

    console.log(data);
    $.post("/comments/save", data)
        .done(function(data) {
            if(data.status='success') {
                alert( 'Your comment was added!');
                $('#commentsDiv').html('');
                window.location.reload() ;
            }
            else {
                alert( data.errorMsg);
                window.location = "/login";
            }
        });
    return false;
}
/*
This function calls the /logout API & redirects the user to login page.
 */
function logout() {
    document.getElementById('logout').style.display = "none";
    $.get("/logout")
        .done(function(data) {
            console.log(data);

            window.location.reload() ;
        });
}
/*
This function triggers the /users/save API for registering a new user.
 */
function register() {
    console.log("in register");

    var name = $('#username').val();
    if (name == null || name == '' || typeof(name) != "string") {
        alert("Please enter a valid Name.");
        return;
    }
    name = name.trim();
    var pwd = $('#password').val();
    if (pwd == null || pwd == '' || typeof(pwd) != "string") {
        alert("Please enter a valid Password.");
        return;
    }
    pwd = pwd.trim();

    var email = $('#email').val();
    if (email == null || email == '' || typeof(email) != "string") {
        alert("Please enter a valid Email.");
        return;
    }
    email = email.trim();


    var data = {};
    data['email'] = email;
    data['name'] = name;
    data['password'] = pwd;

    console.log(data);
    $.post("/users/save", data)
        .done(function(data) {
            if(data.status='success') {
                alert( 'Registration successful!');
                window.location = "/login";
            }
            else {
                alert( data.errorMsg);
            }
        })
        .fail(function(data) {
            alert( "Error!\nEmail ID already exists:" + email + ".\nPlease use a different email to register, or login with your existing credentials." );
        });

}