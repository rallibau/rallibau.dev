import React, { Component } from 'react';
import Input from    '../Form/Input';
import TextArea from '../Form/TextArea';
import Button from   '../Form/Button';
import Form from     '../Form/Form';
import { postPost } from '../../utils/api';
import styled from 'styled-components';
import resumeData from '../../resumeData';
import VerticalBar from '../../components/VerticalBar/VerticalBar';


const Container = styled.div`
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  width: 100%;
  margin-top: 50px;
`;

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
      .then((res) =>
        console.log(res)
      )
      .catch((err) => console.log(err));
  }

  render() {
    const { title, body } = this.state;

    return (
        <Container className="row">
            <div className="three columns align-left">
                <VerticalBar resumeData={resumeData}/>
            </div>
            <div className="nine columns main-col">
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
            </div>
          </Container>

    );
  }
}

export default PostForm;