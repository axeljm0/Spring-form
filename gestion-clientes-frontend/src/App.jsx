import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import ListClientesComponent from './components/ListClientesComponent';
import AddClienteComponent from './components/AddClienteComponent';
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <HeaderComponent />
      <div className="container main-content">
        <Routes>
          <Route path="/" element={<ListClientesComponent />} />
          <Route path="/clientes" element={<ListClientesComponent />} />
          <Route path="/add-cliente" element={<AddClienteComponent />} />
          <Route path="/edit-cliente/:id" element={<AddClienteComponent />} />
        </Routes>
      </div>
      <FooterComponent />
    </BrowserRouter>
  );
}

export default App;
