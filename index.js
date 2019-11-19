const express = require("express")

const app = express()
const port = 4269
app.use(express.json())// parsea todos los json que entran 

app.get("/prueba",(req,rest)=>{
	rest.send("hola")
})

app.listen(port,() => {
	console.log("App running")
})
3
app.post("/graphs",(req,res)=>{
	try{
		//crear el grafo
		const grafo= "a"// hay que cambiar por el grafo
		const newjson = { "id":grafo} //cambiar por el id del grafo
	}
	catch(e){
		rest.set("status",500) 
		res.status(500).send()

	}
})

app.delete("/graphs",(req,res)=>{
	//llamar a java para borrar los grafos?
})

app.delete("/graphs/:id".(req,res)=>{
	const graphId = req.params.id
	const grap_borrado = "aqui se llama a la funcion"
	if (grap_borrado==null){
		rest.set("status",404) 
		res.status(404).send()
	}
	else if (grap_borrado!=null){
		rest.set("status",200) 
		res.status(200).send()
	}

})

get.get("/graphs/:id/nodes",(req,res)=>{
	const graphId = req.params.id
	const list="lista de nodos traida de java"
	rest.send(list)
})

app.delete("/graphs/:id/nodes/:id2",(req,res)=>{
	const graphId = req.params.id
	const nodoId = req.params.id2
	const nodo= "nodo eliminado"
	//llamar a java para borrar los grafos con el id?
	if (nodo==null){
		rest.set("status",404) 
		res.status(404).send()
	}
	else if (nodo!=null){
		rest.set("status",200) 
		res.status(200).send()
	}
})

app.get("/graphs/:id/edges",(req,res)=>{
	const Id = req.params.id2
	const arr= "array con las aristas"
})

app.post("/graphs/{id}/edges/",(req,res)=>{


	
})







app.get("/prueba/:par1",(req,res)=>{
	const parametro = req.params.par1
	res.send(parametro)
})

app.get("/pruebaQuery",(req,rest)=> {
	rest.send(req.query.q1)
})

app.post("/pruebajason",(req,rest) =>{
	const nomnbre = req.body.entity.nombre
	const edad = req.body.entity.edad
	console.log(nombre)
	const newjson = { "edad":133} // crear un json
	rest.send(newjson)
})

app.put("*",(req,res)=>{
	rest.set("status",404) //agregar al header
	res.status(500).send()
})