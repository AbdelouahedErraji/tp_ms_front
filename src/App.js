import Product from "./components/Product";
import Achat from "./components/Achat";
import { Route, Routes } from "react-router-dom";


function App() {
  return (
    <>
      <div>
        <Routes>
          <Route path="/" element={<Product />} />
          <Route path="/achat" element={<Achat />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
