import React, { Component } from 'react';
import Input from    '../Form/Input';
import TextArea from '../Form/TextArea';
import Button from   '../Form/Button';
import Form from     '../Form/Form';
import { postPost } from '../../utils/api';

class PostForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      title: '',
      body: '',
    }
  }

  handleChange = (e) => {
    const target = e.target;
    const name = target.name;
    const value = target.value;

    this.setState({
      [name]: value,
    })
  }

  handleSubmit = (e) => {
    e.preventDefault();

    postPost(this.state)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  }

  render() {
    const { title, body } = this.state;

    return (
      <Form onSubmit={this.handleSubmit}>
        <Input
          name="title"
          onChange={this.handleChange}
          placeholder="title"
          value={title}
        />
        <TextArea
          name="body"
          onChange={this.handleChange}
          placeholder="body"
          value={body}
        />
        <Button>Submit</Button>
      </Form>
    );
  }
}

export default PostForm;