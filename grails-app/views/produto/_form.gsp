<%@ page import="infracom.Produto" %>



<div class="fieldcontain ${hasErrors(bean: produtoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="produto.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="nome" cols="40" rows="5" maxlength="255" required="" value="${produtoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: produtoInstance, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="produto.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="valor" value="${fieldValue(bean: produtoInstance, field: 'valor')}" required=""/>

</div>

