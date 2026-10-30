import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import ClienteService from '../services/ClienteService';

export const AddClienteComponent = () => {
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState('');
  const [email, setEmail] = useState('');
  const [errorMsg, setErrorMsg] = useState('');

  const navigate = useNavigate();
  const { id } = useParams();

  const title = () => {
    if (id) {
      return <h2 className="text-center">Actualizar Cliente</h2>;
    } else {
      return <h2 className="text-center">Registrar Cliente</h2>;
    }
  };

  useEffect(() => {
    if (id) {
      ClienteService.getClienteById(id)
        .then((response) => {
          setNombre(response.data.nombre);
          setApellido(response.data.apellido);
          setEmail(response.data.email);
        })
        .catch((error) => {
          console.error('Error al obtener cliente:', error);
          setErrorMsg('No se pudo encontrar el cliente.');
        });
    }
  }, [id]);

  const saveOrUpdateCliente = (e) => {
    e.preventDefault();

    if (!nombre.trim() || !apellido.trim() || !email.trim()) {
      setErrorMsg('Por favor, complete todos los campos obligatorios.');
      return;
    }

    const cliente = { nombre, apellido, email };

    if (id) {
      ClienteService.updateCliente(id, cliente)
        .then(() => {
          navigate('/clientes');
        })
        .catch((error) => {
          console.error('Error al actualizar cliente:', error);
          setErrorMsg('Ocurrió un error al actualizar el cliente.');
        });
    } else {
      ClienteService.createCliente(cliente)
        .then(() => {
          navigate('/clientes');
        })
        .catch((error) => {
          console.error('Error al registrar cliente:', error);
          setErrorMsg('Ocurrió un error al registrar el cliente.');
        });
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="card col-md-6 shadow-sm p-4">
          <div className="card-header bg-transparent border-0 mb-3">
            {title()}
          </div>
          <div className="card-body">
            {errorMsg && (
              <div className="alert alert-danger" role="alert">
                {errorMsg}
              </div>
            )}
            <form onSubmit={saveOrUpdateCliente}>
              <div className="form-group mb-3">
                <label className="form-label font-weight-bold">Nombre:</label>
                <input
                  type="text"
                  placeholder="Ingrese el nombre"
                  name="nombre"
                  className="form-control"
                  value={nombre}
                  onChange={(e) => setNombre(e.target.value)}
                  required
                />
              </div>

              <div className="form-group mb-3">
                <label className="form-label font-weight-bold">Apellido:</label>
                <input
                  type="text"
                  placeholder="Ingrese el apellido"
                  name="apellido"
                  className="form-control"
                  value={apellido}
                  onChange={(e) => setApellido(e.target.value)}
                  required
                />
              </div>

              <div className="form-group mb-4">
                <label className="form-label font-weight-bold">Email:</label>
                <input
                  type="email"
                  placeholder="Ingrese el email"
                  name="email"
                  className="form-control"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>

              <div className="d-flex justify-content-start gap-2">
                <button type="submit" className="btn btn-success">
                  Guardar
                </button>
                <Link to="/clientes" className="btn btn-danger">
                  Cancelar
                </Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddClienteComponent;
