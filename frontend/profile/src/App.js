import logo from './logo.svg';
import './App.css';
import { ThemeProvider, StyleReset } from 'atomize';

const theme = {
  colors: {
    primary: 'tomato',
    accent: 'yellow',
  },
};

function App() {
  return (
    <ThemeProvider theme={theme}>
        <StyleReset />
        <div className="App">
          hola mon
        </div>
    </ThemeProvider>
  );
}

export default App;
