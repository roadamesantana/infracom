package infracom

class ItemCompra {
	String id
	Produto produto
	int quantidade
	
	static mapping = {
		version false
		id generator:'uuid', name:'id'
		table 'ITEM_COMPRA'
	}

    static constraints = {
		id nullble:false
		produto nullble:false
		quantidade min:1
    }
}
