import React from 'react';
import ReactDOM from 'react-dom';
import {Body} from "./Body";

it('Empty body', () => {
    const table = document.createElement('table');
    const questions = [];
    ReactDOM.render(<Body questions={questions}/>, table);
    expect(table.getElementsByTagName("tr").length).toEqual(0);
    ReactDOM.unmountComponentAtNode(table);
});


it('Validate body', () => {
    const table = document.createElement('table');
    const questions = [
        {"owner": {}, "tags": []},
        {"owner": {}, "tags": []},
        {"owner": {}, "tags": []}
        ];
    ReactDOM.render(<Body questions={questions}/>, table);
    expect(table.getElementsByTagName("tr").length).toEqual(questions.length);
    ReactDOM.unmountComponentAtNode(table);
});
