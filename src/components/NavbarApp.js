import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Container } from "react-bootstrap";

import React from 'react';
import { Link } from 'react-router-dom';

const NavbarApp = () => {
    return (

        <Navbar expand="lg" className="bg-body-tertiary" data-bs-theme="dark">
            <Container>
                <Navbar.Brand>Exchange App</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link>
                            <Link style={{textDecoration: "none", color: "#FFF"}} to="/">Products</Link>
                        </Nav.Link>
                        <Nav.Link>
                            <Link style={{textDecoration: "none", color: "#FFF"}} to="/achat">Achats</Link>
                        </Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )

}

export default NavbarApp;