import React, { useEffect, useState } from 'react'
import { Button, Modal, Form, Table } from 'react-bootstrap'
import { useForm } from 'react-hook-form'
import apiProduct from '../connection/axios'

function Product() {
  const [products, setProducts] = useState([]);
  const [show, setShow] = useState(false);
  const [showDel, setShowDel] = useState(false);
  const [id, setId] = useState(0);
  const [delId, setDelId] = useState(0);
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0.0);
  const [category, setCategory] = useState("");

  const handleClose = () => setShow(false);

  const handleUpdate = () => {
    apiProduct.post("/update/" + id, {
      "name": name,
      "category": category,
      "price": price
    })
      .then((rep) => {
        console.log(rep.data);
        getProducts()
      })
      .catch((err) => {
        console.log(err)
      })
    setShow(false);
  }
  const handleShow = (obj) => {
    setName(obj.name);
    setCategory(obj.category);
    setPrice(obj.price);
    setId(obj.id);
    setShow(true);
  }
  //------------------------------------
  const handleCloseDel = () => setShowDel(false);
  const handleShowDel = (id) => {
    setDelId(id);
    setShowDel(true);
  }

  const deletProduct = () => {
    apiProduct.delete("/delete/" + delId)
      .then((rep) => {
        console.log(rep.data)
        getProducts()
        setShowDel(false);
      })
      .catch((err) => console.log(err))
  }
  //------------------------------------------
  const addprduct = (data) => {

    apiProduct.post('/add', data)
      .then(rep => {
        console.log(rep.data)
        getProducts()
      })
      .catch(err => console.error(err))

  }
  //----------------allProduct------------------------
  const getProducts = () => {
    apiProduct.get('/products')
      .then(rep => {
        setProducts(rep.data)
        console.log(rep.data)
      })
      .catch(err => console.error(err))
  }

  useEffect(
    () => {
      getProducts();
    }
    , [])

  //---------------------------------------
  const { register, handleSubmit } = useForm()
  return (
    <div>
      <Form onSubmit={handleSubmit(addprduct)}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>ProductName</Form.Label>
          <Form.Control {...register("name")} type="text" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Category</Form.Label>
          <Form.Control {...register("category")} type="text" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Price</Form.Label>
          <Form.Control {...register("price")} type="text" />
        </Form.Group>

        <Button type='submit' className='btn-success col-6 offset-3'>Add</Button>
      </Form>
      <h2 className='text-danger'>Liste des produits</h2>
      <Table striped bordered hover>
        <thead>
          <tr>

            <th>ProductName</th>
            <th>Category</th>
            <th>Price</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map(p => <tr key={p.id}>
            <td>{p.name}</td>
            <td>{p.category}</td>
            <td>{p.price}</td>
            <td>
              <Button className='btn btn-success' onClick={() => handleShow(p)}>Update</Button>
              <Button className='btn btn-danger' onClick={() => handleShowDel(p.id)} >del</Button>
            </td>
          </tr>)}


        </tbody>
      </Table>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Product update</Modal.Title>
        </Modal.Header>
        <Modal.Body>

          <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>ProductName</Form.Label>
              <Form.Control value={name} onChange={(e) => setName(e.target.value)} type="text" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Category</Form.Label>
              <Form.Control value={category} onChange={(e) => setCategory(e.target.value)} type="text" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Price</Form.Label>
              <Form.Control value={price} onChange={(e) => setPrice(e.target.value)} type="text" />
            </Form.Group>
          </Form>

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="success" onClick={handleUpdate}>
            Update
          </Button>
        </Modal.Footer>
      </Modal>


      {/* ============Delete ==================== */}
      <Modal show={showDel} onHide={handleCloseDel}>
        <Modal.Header closeButton>
          <Modal.Title>Delete</Modal.Title>
        </Modal.Header>
        <Modal.Body>Voulez-vous vraiment supprimer?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseDel}>
            Non
          </Button>
          <Button variant="primary" onClick={() => deletProduct()}>
            Oui
          </Button>
        </Modal.Footer>
      </Modal>


    </div>


  )
}

export default Product