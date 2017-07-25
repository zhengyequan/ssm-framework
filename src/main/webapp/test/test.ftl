<!DOCTYPE html>
<html>
<head>
	<script src="../resources/js/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
        jQuery.post('http://localhost:8080/baba-web/test/testJsonError.do',
		{
		},
		function(data, status){
			data = eval('(' + data + ')');
			console.log(data.result);
			console.log(data.data);
			console.log(data.message);
		});
	});
	</script>
</head>
<body>
	测试页面，测试测试
</body>
</html>