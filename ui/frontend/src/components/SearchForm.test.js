import React from 'react';
import ReactDOM from 'react-dom';
import {SearchForm} from "./SearchForm";

it('Search form', () => {
  const div = document.createElement('div');
  ReactDOM.render(<SearchForm />, div);
  expect(div.firstChild.textContent).toEqual('Stack overflow searcherSearch');
  ReactDOM.unmountComponentAtNode(div);
});
