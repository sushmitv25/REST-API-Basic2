<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	//console.log("ready");
	
	//var $post_example = $('#post_example');
	alert("hello");
	/**
	 * This is for the Submit button
	 * It will trigger a ajax POST call to: api/v2/inventory
	 * This will submit a item entry to our inventory database
	 */
	$('#delete_rec').click(function(e) {
		//console.log("submit button has been clicked");
		//e.preventDefault(); //cancel form submit	
		ajaxObj = {  
			type: "GET",
			url: "http://localhost:8080/Training_RestAPI/webapi/myresource/Delete/", 
			data: JSON.stringify(jsObj), 
			contentType:"application/json",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				//console.log(data);
				if(data[0].HTTP_CODE == 200) {
					$('#div_ajaxResponse').text( data[0].MSG);
				}
			},
			complete: function(XMLHttpRequest) {
				//console.log( XMLHttpRequest.getAllResponseHeaders() );
				getFreindsData();
			}, 
			dataType: "json" //request JSON
		};
		
		$.ajax(ajaxObj);
	});
	
function Insertdata() {
	
	var fname=$("#fname").val();
	var lname=$("#lname").val();
	var age=$("#age").val();
	var address=$("#address").val();
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:8080/Training_RestAPI/webapi/myresource/Insert/"+fname+"/"+lname+"/"+age+"/"+address, 
			contentType:"application/json",
			success: function(data) { 
				getFreindsData();
			},
			complete: function(XMLHttpRequest) {
			}
		};
		
	return $.ajax(ajaxObj);
}

function getFreindsData() {
	
	ajaxObj = {  
			type: "GET",
			url: "http://localhost:8080/Training_RestAPI/webapi/myresource", 
			contentType:"application/json",
			success: function(data) { 
				var html_string = "";
				$.each(data, function(index1, val1) {
					html_string = html_string + templateGetInventory(val1);
				});
				
				$('#get_friendsData').html("<table style='background-color:bisque' border='5'>" + 
						
					'<tr>' +
						'<th> FIRST_NAME' + '</th>' +
						'<th> LAST_NAME' + '</th>' +
						'<th> Address' + '</th>' + 
						'<th> Age' + '</th>' +
					'</tr>'+
						html_string + "</table>");
			},
			complete: function(XMLHttpRequest) {
			}
		};
		
	return $.ajax(ajaxObj);
}

	function templateGetInventory(param) {
		return '<tr>' +
					'<td class="name">' + param.FIRST_NAME + '</td>' +
					'<td class="interest">' + param.LAST_NAME + '</td>' +
					'<td class="developer">' + param.ADDRESS + '</td>' +
					'<td class="ctc">' + param.AGE + '</td>' +
					'<td class="ctc"><button id="delete_rec">DELETE</button></td>' +
				'</tr>';
		
		}
	
</script>

<input type="submit" value="GETDATA" onclick="getFreindsData()"/>
<div id="get_friendsData"></div>
<form>
First Name<input type="text" name="fname" id="fname"/><br>
Last name<input type="text" name="lname" id="lname"/><br>
Age<input type="text" name="age" id="age"/><br>
Address<input type="text" name="address" id=address/><br>
<input type="button" value="ADD" onclick="Insertdata()"/>
</form>
</body>

</html>