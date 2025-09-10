import React, {useState} from 'react';
import axios from 'axios';

const API = axios.create({ baseURL: 'http://localhost:8080' });

function App(){
  const [token, setToken] = useState(null);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [acc, setAcc] = useState(null);
  const [accNo, setAccNo] = useState('');
  const [to, setTo] = useState('');
  const [amount, setAmount] = useState('');

  const login = async () => {
    const res = await API.post('/api/auth/login', { username, password });
    setToken(res.data.token);
    API.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
  };
  const register = async () => {
    const res = await API.post('/api/auth/register', { username, password });
    setToken(res.data.token);
    API.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
  };
  const createAccount = async () => {
    const res = await API.post('/api/accounts/create');
    setAcc(res.data);
    setAccNo(res.data.accountNumber);
  };
  const transfer = async () => {
    await API.post('/api/tx/transfer', { from: accNo, to, amount });
    alert('Transfer done');
  };
  const getHistory = async () => {
    const res = await API.get('/api/tx/history/' + accNo);
    alert(JSON.stringify(res.data));
  };

  return (
    <div style={{padding:20}}>
      <h2>Online Banking (Demo)</h2>
      {!token ? (
        <div>
          <input placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} />
          <input placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)} />
          <button onClick={login}>Login</button>
          <button onClick={register}>Register</button>
        </div>
      ) : (
        <div>
          <p>Authenticated</p>
          <button onClick={createAccount}>Create Account</button>
          {acc && <div>
            <p>Account: {acc.accountNumber}</p>
            <p>Balance: {acc.balance}</p>
          </div>}
          <h3>Transfer</h3>
          <input placeholder="to account" value={to} onChange={e=>setTo(e.target.value)} />
          <input placeholder="amount" value={amount} onChange={e=>setAmount(e.target.value)} />
          <button onClick={transfer}>Send</button>
          <button onClick={getHistory}>History</button>
        </div>
      )}
    </div>
  );
}

export default App;
