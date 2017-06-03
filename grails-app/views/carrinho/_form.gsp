<%@ page import="infracom.Carrinho" %>



<div class="fieldcontain ${hasErrors(bean: carrinhoInstance, field: 'produtos', 'error')} ">
	<label for="produtos">
		<g:message code="carrinho.produtos.label" default="Produtos" />
		
	</label>
	<g:select name="produtos" from="${infracom.Produto.list()}" multiple="multiple" optionKey="id" size="5" value="${carrinhoInstance?.produtos*.id}" class="many-to-many"/>

</div>

