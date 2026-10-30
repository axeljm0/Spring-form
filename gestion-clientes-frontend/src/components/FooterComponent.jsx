import React from 'react';

export const FooterComponent = () => {
  return (
    <div>
      <footer className="footer">
        <span className="text-muted">
          Sistema de Gestión de Clientes - FullStack Spring Boot & React &copy; {new Date().getFullYear()}
        </span>
      </footer>
    </div>
  );
};

export default FooterComponent;
