import { useState, useEffect } from 'react';
import { Card, CardMedia, CardContent, CardActions, Collapse, Typography, Button, Stack } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

export default function CharacterCard() {
  const [characters, setCharacters] = useState<any[]>([]);  
  const [index, setIndex] = useState(0);
  const [expanded, setExpanded] = useState(false);
  const hasNext = index < characters.length - 1;
  const hasBack = index > 0;

  useEffect(() => {
    fetch('http://localhost:8080/deloria/personalities')  
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();  
      })
      .then((data) => {
        setCharacters(data);  
      })
      .catch((error) => {
        console.error('There was an error fetching the personalities:', error);
      });
  }, []);

  function handleNextClick() {
    if (hasNext) {
      setIndex(index + 1);
    } else {
      setIndex(0);
    }
  }

  function handleBackClick() {
    if (hasBack) {
      setIndex(index - 1);
    } else {
      setIndex(characters.length - 1);
    }
  }

  function handleExpandClick() {
    setExpanded(!expanded);
  }

  if (!characters.length) {
    return <div>NO DATA</div>;  
  }

  let character = characters[index];

  return (
    <div className="character-box">
      <div className="character-header">
        <Card sx={{ maxWidth: 345 }} className="character-card">
          <h2>FAV CHARACTER</h2>
          <h5>Jordan Micko Deloria - C-PEITEL3</h5>
          <h3>
            {index + 1} of {characters.length}
          </h3>

          <Stack spacing={2} direction="row" justifyContent="center" sx={{ marginBottom: 2 }}>
            <Button variant="contained" onClick={handleBackClick} disabled={!hasBack}>BACK</Button>
            <Button variant="contained" onClick={handleNextClick} disabled={!hasNext}>NEXT</Button>
          </Stack>

          <CardMedia
            component="img"
            image={character.url}
            alt={character.alt}
            className="character-image"
          />

          <CardContent>
            <Typography variant="h6" className="character-title">{character.name}</Typography>
            <Typography variant="body2" color="text.secondary">FROM {character.artist}</Typography>
          </CardContent>

          <CardActions className="card-actions" disableSpacing>
            <Button
              onClick={handleExpandClick}
              aria-expanded={expanded}
              aria-label="show more"
              sx={{
                transform: expanded ? 'rotate(180deg)' : 'rotate(0deg)',
                transition: 'transform 0.3s ease',
              }}
            >
              <ExpandMoreIcon />
            </Button>
          </CardActions>
          <Collapse in={expanded} timeout="auto" unmountOnExit>
            <CardContent>
              <Typography>{character.description}</Typography>
            </CardContent>
          </Collapse>
        </Card>
      </div>
    </div>
  );
}
