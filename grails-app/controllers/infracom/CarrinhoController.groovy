package infracom



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CarrinhoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def listar() {
		Carrinho carrinhoInstance = Carrinho.list()[0]
		def itensCarrinho = carrinhoInstance.itens.sort { x, y ->
			y.produto.nome <=> x.produto.nome
		}
		
		int qtdItens = 0
		itensCarrinho.each{
			qtdItens += it.quantidade
		}
		
		render ( template:'carrinhoList', model:[carrinhoInstanceCount: qtdItens, itensCarrinho:itensCarrinho] )
	}

    def index(Integer max) {
		render ( view:"index" )
    }

    def show(Carrinho carrinhoInstance) {
        respond carrinhoInstance
    }

    def edit(Carrinho carrinhoInstance) {
        respond carrinhoInstance
    }

    @Transactional
    def update(Carrinho carrinhoInstance) {
        if (carrinhoInstance == null) {
            notFound()
            return
        }

        if (carrinhoInstance.hasErrors()) {
            respond carrinhoInstance.errors, view:'edit'
            return
        }

        carrinhoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Carrinho.label', default: 'Carrinho'), carrinhoInstance.id])
                redirect carrinhoInstance
            }
            '*'{ respond carrinhoInstance, [status: OK] }
        }
    }
	
	def colocarNoCarrinho( String id ) {
		Carrinho carrinho = Carrinho.list()[0]
		
		def itemCompra = carrinho.itens.find() {
			it.produto.id ==~ id
		}
		
		if ( null== itemCompra ) {
			itemCompra = new ItemCompra()
			itemCompra.produto = Produto.get( id )
		}
		
		itemCompra.quantidade = itemCompra.quantidade + 1
		
		itemCompra.validate()
		if ( !itemCompra.hasErrors() ) {
			itemCompra.save( flush:true )
		} else {
			println("Error ao salvar item. " + itemCompra.class )
		}
		
		carrinho.itens.add( itemCompra )
		
		carrinho.validate()
		if ( !carrinho.hasErrors() ) {
			carrinho.save( flush:true )
		} else {
			println("Error ao salvar item. " + carrinho.class )
		}
		
		redirect( action: "index" )
	}
	
	def diminuirUm() {
		ItemCompra itemCompra = ItemCompra.get( params.id )		
		itemCompra.quantidade = itemCompra.quantidade - 1
		
		def retorno = ['msg':'ERROR']
		
		if ( itemCompra.quantidade <= 0 ) {
			
			excluirItemCarrinho( itemCompra )
			
			if ( salvarCarrinho( Carrinho.list()[0] ) ) {
				retorno['msg'] = 'OK'
			}
		} else {
			if ( salvarItemCompra( itemCompra ) ) {
				retorno['msg'] = 'OK'
			}
		}
		
		render retorno as JSON
	}
	
	def somarUm() {
		ItemCompra itemCompra = ItemCompra.get( params.id )				
		itemCompra.quantidade = itemCompra.quantidade + 1
		
		def retorno = ['msg':'ERROR']
		
		if ( salvarItemCompra( itemCompra ) ) {
			retorno['msg'] = 'OK'
		}
		
		render retorno as JSON
	}
	
	def excluirItem() {
		excluirItemCarrinho( ItemCompra.get( params.id ) )
		
		def retorno = ['msg':'ERROR']
		
		if ( salvarCarrinho( Carrinho.list()[0] ) ) {
			retorno['msg'] = 'OK'
		}
		
		render retorno as JSON
	}
	
	def limparCarrinho() {
		Carrinho carrinho = Carrinho.list()[0]
		carrinho.itens.clear()
		
		def retorno = ['msg':'ERROR']
		
		if ( salvarCarrinho( carrinho ) ) {
			retorno['msg'] = 'OK'
		}
		
		render retorno as JSON
	}
	
	/**
	 * Valida as constraints do <code>Carrinho</code> e salva no banco. 
	 * 
	 * @param carrinho Objecto <code>Carrinho</code> que está sendo salvo.
	 * @return <code>true</code> - Caso o <code>Carrinho</code> tenha sido salvo com sucesso.
	 *         <code>false</code> - Caso tenha ocorrido algum erro ao salvar o <code>Carrinho</code>.
	 */
	private boolean salvarCarrinho( Carrinho carrinho ) {
		boolean carrinhoSalvo = false;
		
		carrinho.validate()
		if ( !carrinho.hasErrors() ) {
			carrinho.save( flush:true )
			carrinhoSalvo = true;
		}
		
		return carrinhoSalvo;
	}
	
	/**
	 * Valida as constraints do <code>ItemCompra</code> e salva no banco.
	 *
	 * @param itemCompra Objecto <code>ItemCompra</code> que está sendo salvo.
	 * @return <code>true</code> - Caso o <code>ItemCompra</code> tenha sido salvo com sucesso.
	 *         <code>false</code> - Caso tenha ocorrido algum erro ao salvar o <code>ItemCompra</code>.
	 */
	private boolean salvarItemCompra( ItemCompra itemCompra ) {
		boolean itemSalvo = false;
		
		itemCompra.validate()
		if ( !itemCompra.hasErrors() ) {
			itemCompra.save( flush:true )
			itemSalvo = true;
		}
		
		return itemSalvo;
	}
	
	/**
	 * Remove o <code>ItemCompra</code> informado do carrinho de compras.
	 * 
	 * @param itemCompra Objeto <code>ItemCompra</code> que está sendo removido.
	 */
	private void excluirItemCarrinho( ItemCompra itemCompra ) {
		Carrinho carrinho = Carrinho.list()[0]
		carrinho.itens.remove( itemCompra )
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'carrinho.label', default: 'Carrinho'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
