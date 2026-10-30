import React from 'react';
import { Link } from 'react-router-dom';

export const HeaderComponent = () => {
  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark">
          <div className="container">
            <Link to="/" className="navbar-brand">
              Gestión de Clientes
            </Link>
          </div>
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
