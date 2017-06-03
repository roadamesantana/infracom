
<%@ page import="infracom.Carrinho" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'carrinho.label', default: 'Carrinho')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#divErroExibir").hide()
				carregarCarrinho()
			})
			
			function carregarCarrinho(data) {
				$.ajax({
					method: "POST",
					url: "listar",
					data: {},
					success: function(data){
						$("#divCarrinho").html(data)
					}					
				})
			}

			function atualizar(data) {
				if ( data.msg != "OK" ) {
					$("#divErroExibir").show()
					$("#divErroExibir").html("Não foi possível efetuar essa operação no momento.")
					return
				} 

				carregarCarrinho(data)
			}
		</script>
	</head>
	<body>
		<a href="#list-carrinho" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li>
					<g:remoteLink action="limparCarrinho" onSuccess="atualizar(data)">
						<asset:image src="clear.png" alt="limpar" />&nbsp;&nbsp;Esvaziar Carrinho
					</g:remoteLink>
				</li>
			</ul>
		</div>
		<div id="divErroExibir" style="color:red;padding:10px;float:left;margin:20px;width:94%;
				box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"></div>
		<div id="divCarrinho" style="width:100%;"></div>
	</body>
</html>
