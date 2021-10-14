import React from 'react';
import styled from 'styled-components';
const ContainerPost = styled.div`
  box-sizing: border-box;
  flex-grow: 1;
  min-width: 280px;
  width: 100%;
  background-color: #fff;
  text-align:left;
`;
const Post = ({ title, body }) => {
  return (
    <ContainerPost>
      <h1>{title}</h1>
      <p>{body}</p>
      <hr />
    </ContainerPost>
  );
};
export default Post;