import infracom.*

class BootStrap {

    def init = { servletContext ->
		Produto a = new Produto( nome:"iPhone 7", valor:3499.99 ).save()
		new Produto( nome:"Galaxy J7", valor:1299.99 ).save()
		new Produto( nome:"LG X Style", valor:499.99 ).save()
		new Produto( nome:"Alcatel Pixi", valor:490.85 ).save()
		new Produto( nome:"Zenfone Go", valor:399.00 ).save()
		new Produto( nome:"Vibe K6", valor:729.90 ).save()
		new Produto( nome:"Galaxy A5", valor:1699.99 ).save()
		new Produto( nome:"Moto G4", valor:929.90 ).save()
		new Produto( nome:"Multilaser MS50", valor:359.90 ).save()
		new Produto( nome:"Quantum MÜV", valor:599.90 ).save()
		
		Carrinho c = new Carrinho().save()
		
//		ItemCompra ic = new ItemCompra()
//		ic.produto = a
//		ic.quantidade = 1
//		ic.save()
		
		Set<ItemCompra> itensCompra = new HashSet()
//		itensCompra.add(ic)
		
		c.itens = itensCompra
		
		c.save()
    }
    def destroy = {
    }
}
