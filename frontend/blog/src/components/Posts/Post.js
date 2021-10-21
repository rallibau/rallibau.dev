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
const Post = ({ id,title, body,author }) => {
  return (
    <ContainerPost>
      <h1><a href={"blog/"+id}>{title}</a></h1>
      <p>{body}</p>
      <hr />
      by {author}
      <hr />
    </ContainerPost>
  );
};
export default Post;