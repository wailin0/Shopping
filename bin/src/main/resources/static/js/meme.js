<script>
$(document).ready(function(){
    $.getJSON("/info/1", function(data){
        $("#username").append(data.username);
        $("#email").append(data.email);
        $("#phone").append(data.phone);
    });
});
</script>

<!-- for updating only username   -->
<script type="text/javascript">
	$(document).ready(function(){
	$("#updateUsernameAction").on("click",function(){
		var name = $("#updateUsername").val();
		$.ajax({
			url: "/info/4",
			type: "PUT",
			contentType: "application/json",
			data: JSON.stringify({"username":name}),
			success: function(data) {
				$("#msg").html( "<span style='color: green'>Username updated successfully</span>" );
				window.setTimeout(function(){location.reload()},1000)
			},
			error: function(err) {
				$("#msg").html( "<span style='color: red'>Name is required</span>" );
			}
		})
		});
	});
</script>
