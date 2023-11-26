import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import NavbarApp from './components/NavbarApp';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <NavbarApp />
      <br /><br />
      <Container>
        <App />
      </Container>
    </Router>
  </React.StrictMode>
);
reportWebVitals();
