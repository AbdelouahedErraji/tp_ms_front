import React, { useEffect, useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import apiAchat from '../connection/axios1';
import { Table } from 'react-bootstrap';

const AchatDetails = ({ achatId, closeModal }) => {
    const [achatProducts, setAchatProducts] = useState([]);

    console.log(achatProducts);

    const getAchatProducts = () => {
        apiAchat.get(`/${achatId}`)
            .then(rep => {
                setAchatProducts(rep.data.products)
            })
            .catch(err => console.error(err))
    }

    useEffect(() => {
        getAchatProducts();
    });

    return (
        <Modal show={true} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>Achat Details</Modal.Title>
            </Modal.Header>
            <Modal.Body>

                <div>
                    <Table striped bordered hover>
                        <thead>
                            <tr>

                                <th>ProductName</th>
                                <th>Category</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            {achatProducts.map(p => <tr key={p.id}>
                                <td>{p.name}</td>
                                <td>{p.category}</td>
                                <td>{p.price}</td>
                            </tr>)}


                        </tbody>
                    </Table>
                </div>

            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={closeModal}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default AchatDetails;