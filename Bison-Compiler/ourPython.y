%{

/*-------------------------------------------------------------------------*
 *---									---*
 *---		ourPython.y						---*
 *---									---*
 *---	    This file defines the parser and high-level functions that	---*
 *---	implement a basic Python interpreter.				---*
 *---									---*
 *---	----	----	----	----	----	----	----	----	---*
 *---									---*
 *---	Version 1a		2018 May 16		Joseph Phillips	---*
 *---									---*
 *-------------------------------------------------------------------------*/

/*-------------------------------------------------------------------------*

				Compile with:
bison -d --debug ourPython.y
g++ -c ourPython.tab.c -g
flex -o ourPython.cpp ourPython.lex 
g++ -c ourPython.cpp -g
g++ -c Object.cpp -g
g++ -c Expression.cpp -g
g++ -c Statement.cpp -g
g++ -o ourPython ourPython.tab.o ourPython.o Object.o Expression.o Statement.o

 *-------------------------------------------------------------------------*/


#include	"ourPython.h"
#define YYDEBUG 1
%}

//  Erase this line and declare a %union here

%error-verbose


%union
{
	Statement* statePtr_;
	Expression* exprPtr_;
    std::string *var_name;
    int int_val;
    float float_val;
    bool bool_val;
}
%type<statePtr_> single_input compound_stmt simple_stmt small_stmt  if_stmt while_stmt proto_if_stmt stmt stmt_rec suite
%type<exprPtr_>  atom term arith_expr expr comparison or_test and_test not_test test   expr_stmt print_stmt testlist_comp
%type<var_name> arith_h_op arith_l_op comp_op
%start		single_input

%token<var_name> VARIABLE_NAME
%token<int_val> INTEGER
%token<float_val> FLOAT
%token<bool_val> TRUE FALSE
%token<var_name> STRING
%token NEWLINE SEMICOLON  BEGIN_INDENT END_INDENT WHILE ELIF
%token KEYWORD_NAME
%token<var_name> ARITH_H_OP ARITH_L_OP COMP_OP 
%token EQUAL_TO IF ELSE AND OR NOT POWER_OP SHIFT_OP B_AND B_OR B_NOT B_XOR FACTOR_OP OPEN_PARAN CLOSE_PARAN COLON
%token PRINT 

%%

single_input    :
                {
                 //printf("NULL");
		 return (1);
                } 
                | NEWLINE
		  {
	            //printf("NEW\n");
		    programRootPtr = $$	= NULL;
		    YYACCEPT;
		  }
		| compound_stmt NEWLINE
		  {
                    //printf("compund_stmt\n");
		    programRootPtr = $$	= $1;
		    YYACCEPT;
		  }
		| simple_stmt
		  {
                    //printf("simple_stmt1\n");
		    programRootPtr = $$	= $1;
		    YYACCEPT;
		  }

simple_stmt : small_stmt NEWLINE 
            | small_stmt SEMICOLON 
small_stmt : expr_stmt {
            $$ = new ExpressionStatement($1); 
            //printf("expr_stmt\n");
            //YYACCEPT;

          }

           | print_stmt
          {
            $$ = new PrintStatement($1); 
            //$$->run();
            //printf("print_stmt\n");
            //YYACCEPT;

          }

print_stmt : PRINT 
         {
            $$ = NULL;
         }
        | PRINT test
         {
           
           $$=$2;
         }
expr_stmt : test {
           $$=$1 ;
          }
          | test EQUAL_TO expr_stmt 
          {
            $$ = new AssignmentExpression($1, $3);
            //printf("assign_stmt\n");
            //YYACCEPT;
          };
test : or_test 
    {
        //printf("or_test\n");
        $$ = $1;
    }
//inline_if_else
//inline_if_else : | IF or_test ELSE test
or_test: and_test OR or_test 
        {
             $$= new BinaryExpression(OR_OP,$1,$3);    

        } 
       | and_test
    {
        //printf("and_test\n");
        $$ = $1;
    }
and_test: not_test AND and_test 
        {
             $$= new BinaryExpression(AND_OP,$1,$3);    
        } 
        | not_test
    {
        //printf("not_test\n");
        $$ = $1;
    }
not_test: NOT not_test 
        {
           $$=new UnaryExpression(NOT_OP, $2);
        }
        | comparison
    {
        //printf("comparison\n");
        $$ = $1;
    }
comparison: expr comp_op comparison 
          {
             operator_ty o;
if(strcmp("<", $2->c_str()) == 0)
    o = LESSER_OP ;
else if(strcmp(">",$2->c_str()) == 0)//|"=="|">="|"<="|"<>"|"!="
    o = GREATER_OP;
else if(strcmp("==", $2->c_str()) == 0)
    o=EQUAL_EQUAL_OP;
else if (strcmp(">=", $2->c_str()) == 0)
    o=GREATER_EQUAL_OP;
else if(strcmp("<=", $2->c_str()) == 0)
    o=LESSER_EQUAL_OP;
else if(strcmp("<>", $2->c_str()) == 0)
    o=NOT_EQUAL_OP;
else if(strcmp("!=", $2->c_str()) == 0)
    o=NOT_EQUAL_OP;

             $$= new BinaryExpression(o,$1,$3);    
              
          }
          | expr
    {
        //printf("expr\n");
        $$ = $1;
    }
comp_op: COMP_OP
       {
          $$=$1;
       }
expr : arith_expr
    {
        //printf("arith_expr\n");
        $$ = $1;
    }
arith_expr: term arith_l_op arith_expr 
          {
operator_ty o;
if(strcmp("+", $2->c_str()) == 0)
o=PLUS_OP;
else if(strcmp("-", $2->c_str()) == 0)
o=MINUS_OP;

             $$= new BinaryExpression(o,$1,$3);    
          }
          | term
    {
        //printf("term\n");
        $$ = $1;
    }
arith_l_op : ARITH_L_OP
           {//"+"|"-"
               //YYACCEPT;
             $$ = $1;
           }
term: atom arith_h_op term 
    {
operator_ty o;
if(strcmp("*", $2->c_str()) == 0)
o=STAR_OP;
else if(strcmp("/", $2->c_str()) == 0)
o=SLASH_OP;
else if(strcmp("%", $2->c_str()) == 0)
o=PERCENT_OP;
else if(strcmp("//", $2->c_str()) == 0)
o=SLASH_SLASH_OP;


        $$= new BinaryExpression(o,$1,$3);    
    }
    | atom
    {
        //printf("atom\n");
        $$ = $1;
    }
arith_h_op : ARITH_H_OP
           {//"*"|"@"|"/"|"%"|"//"
             //printf("ARITH_H_OP\n");
             //YYACCEPT;
             $$ = $1;
           }
atom: VARIABLE_NAME
          {
            //printf("%s\n", $1->c_str());
            $$ = new VariableExpression($1->c_str());
            //YYACCEPT;
          }
     | INTEGER
          {
            $$ = new ConstantExpression($1);
            //YYACCEPT;
          }
     | FLOAT
         {
            $$ = new ConstantExpression($1);
         }
     | OPEN_PARAN testlist_comp CLOSE_PARAN
         {
            $$ = $2;
         }
     | STRING
         {
            //printf("%s", $1->c_str());
            $$ = new ConstantExpression(*$1);
         }
     | TRUE
         {
            $$ = new ConstantExpression($1);
         }
     | FALSE
         {
            $$ = new ConstantExpression($1);
         }
testlist_comp : test

compound_stmt : if_stmt | while_stmt

if_stmt: proto_if_stmt
        {
        $$=$1;
    }
    | proto_if_stmt ELSE COLON suite
    {
        $$=$1;
        $$->appendElse($4);
    }
proto_if_stmt: IF test COLON suite
              {
                    $$=new IfThenElseStatement($2,$4);
              }
    | proto_if_stmt ELIF test COLON suite
    {
        $$=$1;
        $$->appendElif($3,$5);
    }

while_stmt: WHILE test COLON suite
          {
            $$=new WhileStatement($2, $4);
          }
suite: simple_stmt 
     | NEWLINE BEGIN_INDENT stmt_rec END_INDENT
     {
        $$=$3;
     }
stmt: simple_stmt | compound_stmt
stmt_rec:  stmt 
        {
            $$=new BlockStatement();
            $$->addStatement($1);
        }
        | stmt_rec stmt
        {
           $1->addStatement($2);
           $$=$1; 
        }
%%

//  PURPOSE:  To tell the printable names of the values of 'pythonType_ty'.
const char*	typeNameArray[]
      		= {
		    "none",
		    "bool",
		    "int",
		    "float",
		    "str",
		    "type"
		  };


//  PURPOSE:  To tell the printable names of the values of 'operator_ty'.
const char*	operatorNameArray[]
      		= {
		    "OR",
		    "AND",
		    "NOT",
		    "+",
		    "-",
		    "*",
		    "/",
		    "%",
		    "//",
		    "**"
		  };


//  PURPOSE:  To hold the names of boolean constants.
const char*	booleanConstName[]
		= { "False",
		    "True"
		  };


//  PURPOSE:  To serve as a global temporary C-string array.
char		line[LINE_LEN];


//  PURPOSE:  To point to the root of the abstract syntax tree.
Statement*	programRootPtr	= NULL;


//  PURPOSE:  To hold the variables and their values.
VariableStore	variableStore;


//  PURPOSE:  To handle the outputing of parse-time error message 'cPtr'.
//	No return value.
int		yyerror		(const char*	cPtr
				)
{
  throw Exception(cPtr);
}


//  PURPOSE:  To interpret and run the Python program given in 'argv[1]'.
//	Returns 'EXIT_SUCCESS' on success or 'EXIT_FAILURE' otherwise.
int		main		(int		argc,
				 char*		argv[]
				)
{
  //  I.  Application validity check:
//yydebug = 1;
  if  (argc < 2)
  {
    fprintf(stderr,"Usage:\t%s <pythonProg>\n",argv[0]);
    exit(EXIT_FAILURE);
  }

  //  II.  Parse and execute program:
  //  II.A.  Initialize file:
  const char*	pythonFilepath	= argv[1];

  if  ( (yyin = fopen(pythonFilepath,"r")) == NULL )
  {
    fprintf(stderr,"Error opening %s.\n",pythonFilepath);
    exit(EXIT_FAILURE);
  }

  //  II.B.  Attempt to parse and assemble 'yyin':
  int	status	= EXIT_SUCCESS;

  try
  {
    //  II.B.1.  Attempt to parse:
    while  ( !feof(yyin) && (yyparse() == 0) )
    {
      //printf("getting next symbol\n");
      //  II.B.1.a.  Parse was successful and have tree:
      if  (programRootPtr != NULL)
      {

        //  II.B.1.a.I.  It highest Statement was an Expression,
        //  	       then convert it to a Print so output is generated:
        //ExpressionStatement*	exprPtr;

        //exprPtr	= dynamic_cast<ExpressionStatement*>(programRootPtr);
 
       //printf("Printing \n");
        //if  (exprPtr != NULL)
        {
       //printf("Printing\n");
	  //programRootPtr = new PrintStatement(exprPtr->giveAwayExprPtr());

	  //safeDelete(exprPtr);
        }

        //  II.B.1.a.II.  Walk tree to evaluate it:
        programRootPtr->run();

        //  II.B.1.a.III.  Release memory:
        safeDelete(programRootPtr);
      }
    }

  }
  catch  (Exception error)
  {
    fprintf(stderr,"Error: %s\n",error.getDescription().c_str());
    status	= EXIT_FAILURE;
  }

  //  II.C.  Clean up:
  fclose(yyin);

  //  III.  Finished:
  return(status);
}
