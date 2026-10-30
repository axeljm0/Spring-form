import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ClienteService from '../services/ClienteService';

export const ListClientesComponent = () => {
  const [clientes, setClientes] = useState([]);

  useEffect(() => {
    listarClientes();
  }, []);

  const listarClientes = () => {
    ClienteService.getAllClientes()
      .then((response) => {
        setClientes(response.data);
      })
      .catch((error) => {
        console.error('Error al listar clientes:', error);
      });
  };

  const deleteCliente = (clienteId) => {
    if (window.confirm('¿Está seguro de que desea eliminar este cliente?')) {
      ClienteService.deleteCliente(clienteId)
        .then(() => {
          listarClientes();
        })
        .catch((error) => {
          console.error('Error al eliminar cliente:', error);
        });
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Lista de Clientes</h2>
      <Link to="/add-cliente" className="btn btn-primary mb-3">
        Agregar Cliente
      </Link>
      <div className="table-responsive shadow-sm rounded">
        <table className="table table-bordered table-striped table-hover mb-0">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Email</th>
              <th className="text-center">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {clientes.length === 0 ? (
              <tr>
                <td colSpan="5" className="text-center text-muted py-4">
                  No hay clientes registrados actualmente.
                </td>
              </tr>
            ) : (
              clientes.map((cliente) => (
                <tr key={cliente.id}>
                  <td>{cliente.id}</td>
                  <td>{cliente.nombre}</td>
                  <td>{cliente.apellido}</td>
                  <td>{cliente.email}</td>
                  <td className="text-center">
                    <Link
                      to={`/edit-cliente/${cliente.id}`}
                      className="btn btn-info btn-sm me-2"
                    >
                      Actualizar
                    </Link>
                    <button
                      onClick={() => deleteCliente(cliente.id)}
                      className="btn btn-danger btn-sm"
                    >
                      Eliminar
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListClientesComponent;
