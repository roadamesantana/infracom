package infracom

class Produto {
	String id
	String nome
	Float valor
	
	static mapping = {
		version false
		id generator:'uuid', name:'id'
		table 'PRODUTO'
	}

    static constraints = {
		id nullble:false
		nome nullble:false, blank:false, size:1..255, unique:true
		valor nullble:false
    }
	
	@Override
	public String toString() {
		return nome + ' - R$ ' + valor
	}
}
