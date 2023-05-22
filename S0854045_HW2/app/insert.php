<?php
header('Content-Type: application/json; charset=UTF-8'); //設定資料類型為 json，編碼 utf-8

	//--------帳號----------//
	if(@$_POST["account"] != "")
	{
		$account=$_POST["account"];		
	}
	else
	{
		echo json_encode("12");
		$account="";
	}
	//--------內容----------//
	if(@$_POST["content"] != "")
	{
		$content=$_POST["content"];	
		//echo $content;
	}
	else
	{
		$content="";
	}
	echo json_encode($account);
	echo json_encode($content);
	//------------讀取資料庫--------------//	

	$servername = "localhost";
	$username = "root";
	$password = "root123456";
	$dbname = "chatdb";
	$conn = new mysqli($servername, $username, $password, $dbname);

	//------------------------------------//
	// 建立資料庫連線
	$conn = new mysqli($servername, $username, $password, $dbname);
	// 確認資料是否正常連線
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	}
	
	if($account !="")
	{
		$sql = "INSERT INTO `chat` (`no`, `timestamp`, `account`, `content`) VALUES (NULL, current_timestamp(), '".@$account."', '".@$content."');";
		$result = $conn->query($sql);
	}
	else{
		
		echo json_encode("123");
	}

	$messageArr = array();
	$dataarray= array();	
	@$num_rows='';
	if (isset($result->num_rows) && $result->num_rows >0) {
		// 輸出 每一個資料row
		while($row = $result->fetch_assoc()) {
		
			$content=$row["content"];
			$timestamp=$row["timestamp"];
			
		}
	} 

	$conn->close();
	//------------------------------------------------
	//輸出結果JSON格式		
	$dataarray= array(
	"account" => @$account,//
	"content" => @$content,//	
	);	
	$messageArr["data"] = $dataarray;
	//------------------------------------------------------
	//送入時間格式
    $messageArr["status"] = array();
	date_default_timezone_set('America/La_Paz');
	$today = date('Y-m-d\TH:i:sP');//RFC3339格式
	$datetime= array(
	"code" => "0",
	"message" => "Success Insert Message",
	"datetime" => $today
	);	
	$messageArr["status"] = $datetime;	
		


if(!empty($_POST['account']))
{
	http_response_code(200);
    echo json_encode($messageArr);	
}
else
{		
	http_response_code(404);	
	$messageArr["data"] =[];//因為沒有帳號，我們就預設讓它為空陣列
	$messageArr["status"] = array();
	date_default_timezone_set('America/La_Paz');
	$today = date('Y-m-d\TH:i:sP');//RFC3339 format
	$datetime= array(
	"code" => "404",
	"message" => "Error Account is null",
	"datetime" => $today
	);	
	$messageArr["status"] = $datetime;
	echo json_encode($messageArr);	
}
