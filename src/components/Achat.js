import React, { useEffect, useState } from 'react';
import { Button, Table } from 'react-bootstrap';
import apiAchat from '../connection/axios1';
import AchatDetails from './AchatDetails';

function Product() {
  const [achats, setAchats] = useState([]);
  const [selectedAchat, setSelectedAchat] = useState(null);

  const getAchats = () => {
    apiAchat.get('/achats')
      .then(rep => {
        setAchats(rep.data);
      })
      .catch(err => console.error(err));
  }

  const handleDetailsClick = (achatId) => {
    setSelectedAchat(achatId);
  }

  const handleCloseModal = () => {
    setSelectedAchat(null);
  }

  useEffect(() => {
    getAchats();
  }, []);

  return (
    <div>
      <h2 className='text-danger'>Liste des achats</h2>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Date Achat</th>
            <th>Currency</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          {achats.map(achat => (
            <tr key={achat.id}>
              <td>{achat.date}</td>
              <td>{achat.currency}</td>
              <td>{achat.total}</td>
              <td>
                <Button className='btn btn-success' onClick={() => handleDetailsClick(achat.id)}>Details</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      {selectedAchat && <AchatDetails achatId={selectedAchat} closeModal={handleCloseModal} />}
    </div>
  );
}

export default Product;