import React from 'react';
import ReactDOM from 'react-dom';
import {Question} from "./Question";

it('Validate body', () => {
    const tbody = document.createElement("tbody");
    const question = {"owner": {}, "tags": []};
    ReactDOM.render(<Question id={1} question={question}/>, tbody);
    expect(tbody.getElementsByTagName("tr").length).toEqual(1);
    expect(tbody.getElementsByTagName("td").length).toEqual(8);
    ReactDOM.unmountComponentAtNode(tbody);
});