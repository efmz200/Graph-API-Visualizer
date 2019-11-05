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