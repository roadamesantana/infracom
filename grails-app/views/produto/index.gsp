
<%@ page import="infracom.Produto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'produto.label', default: 'Produto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-produto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-produto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<div style="width:100%;">
				<g:each in="${ produtos }" var="produto">
					<div style="padding:10px;float:left;margin:20px;width:250px;
								box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
						<div style="float:right;cursor:pointer;" title="Adicionar ao Carrinho">
							<g:link controller="Carrinho" action="colocarNoCarrinho" id="${ produto.id }">
								<asset:image src="add.png" alt="Adicionar ao Carrinho"/>
							</g:link>
						</div>
						<div style="float:left: ;">
							<h2>${ produto.nome }</h2>
							<h4><g:formatNumber number="${ produto.valor }" type="currency" currencyCode="BRL" /></h4>
						</div>
					</div>	
				</g:each>
			</div>
		</div>
	</body>
</html>
