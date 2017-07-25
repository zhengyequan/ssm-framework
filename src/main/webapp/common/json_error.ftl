{
	<#if errorCode??>
		"result":${errorCode}
	<#else>
		"result": -1
	</#if>
	<#if message??>,"message":${message}</#if>
}