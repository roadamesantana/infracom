<g:set var="total" value="${ 0 }" />
<g:each in="${ itensCarrinho }" var="item">
	<div style="padding:10px;float:left;margin:20px;width:94%;
				box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
		<div style="float:right;vertical-align:middle;">
			<g:remoteLink id="${ item.id }" action="somarUm" onSuccess="atualizar(data)">
				<asset:image src="square-plus.png" alt="mais" title="Adicionar mais um ${ item.produto.nome } ao carrinho" 
					style="float:right;"/>
			</g:remoteLink>
			<h5 style="float:right;margin-left:5px;margin-right:5px;margin-top:3px;">${ item.quantidade }</h5>
			<g:remoteLink id="${ item.id }" action="diminuirUm" onSuccess="atualizar(data)">
				<asset:image src="square-minus.png" alt="menos" title="Diminuir um ${ item.produto.nome } do carrinho"
					style="float:right;"/>
			</g:remoteLink>
			<g:remoteLink id="${ item.id }" action="excluirItem" onSuccess="atualizar(data)">
				<asset:image src="trash.png" alt="remover" title="Remover Item ${ item.produto.nome } do carrinho" 
					style="margin-top:1px;margin-right:15px;" />
			</g:remoteLink>
		</div>
		<div style="float:left: ;">
			<h2>${ item.produto.nome }</h2>
			<h4>R$ ${ item.produto.valor }</h4>
		</div>
		<div style="float:right;vertical-align:middle;">
			<g:set var="subTotal" value="${ item.produto.valor * item.quantidade }" />
			<g:set var="total" value="${ total + subTotal }" />
			<h5>SubTotal: <g:formatNumber number="${ subTotal }" type="currency" currencyCode="BRL" /></h5>
		</div>
	</div>	
</g:each>
<div style="float:left;margin:20px;">
	<g:link controller="Produto" action="index">Continuar comprando...</g:link><br />
</div>
<div style="float:right;text-align:right;">
	<h4 style="margin:20px;">Itens: ${ carrinhoInstanceCount }</h4>
	<h2 style="margin-right:20px;margin-bottom:20px;float:left;">Total: <g:formatNumber number="${ total }" type="currency" currencyCode="BRL" /></h2>
</div>