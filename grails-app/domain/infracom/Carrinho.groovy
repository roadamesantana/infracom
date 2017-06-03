package infracom

class Carrinho {
	String id
	
	static hasMany = [itens:ItemCompra]
	
	static mapping = {
		version false
		id generator:'uuid', name:'id'
		table 'CARRINHO'
	}

    static constraints = {
		id nullble:false
    }
}
